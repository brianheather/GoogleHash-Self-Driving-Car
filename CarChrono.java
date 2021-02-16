/**
 * Variable range removed, might add back to speed up searching for start and end journey times
 * Combined checkRoute and addRoute -->method: insertRoute
 */
package application;

import java.util.ArrayList;
//import java.util.Arrays;

public class CarChrono {

	//private ArrayList<Integer[]> range = new ArrayList<>();
	private ArrayList<Route> deRoute = new ArrayList<>();

	public CarChrono() {
		//range.add(new Integer[] { 0 });
		deRoute.add(new Route(0, 0, 0, 0, 0, 0, 0));
	}

	// priority 1 only
	public boolean appendRoute(Route routeB) {
		Route routeA = deRoute.get(deRoute.size() - 1);
		if (goodTravelTime(routeA, routeB)) {
			deRoute.add(routeB);
			//range.add(new Integer[] { routeB.getStartT(), routeB.getEndT() });
			return true;
		}
		return false;
	}

	// priority 2 only
	public boolean insertRoute(Route routeB) {
		int startT = routeB.getStartT();
		int endT = routeB.getEndT();
		int dist =  routeB.getDist();
				
		int loc = findGoodTRoute(startT, endT, dist);

		int routeLength = 0;
		if (loc != -1) {
			
			Route routeA = deRoute.get(loc);
			int[] axy2 = routeA.getEndL();
			int[] bxy1 = routeB.getStartL();
			int aLbL = calDist(axy2[0], axy2[1], bxy1[0], bxy1[1]); // time from aL2 to bL1
			routeLength += aLbL;
			
			if (loc + 1 == deRoute.size()) {
				deRoute.add(routeB);
				routeB.setT(aLbL, aLbL + dist);
				return true;
			} else {

				routeLength += routeB.getDist(); // time from bL1 to bL2
				int[] bxy2 = routeB.getEndL();

				Route routeC = deRoute.get(loc + 1);
				int at2 = routeA.getEndT();

				Route tempBRoute = new Route(0, 0, bxy2[0], bxy2[1], 0, at2 + routeLength, 0);
				if (goodTravelTime(tempBRoute, routeC)) {
					deRoute.add(loc + 1, routeB);
					routeB.setT(aLbL, aLbL + dist);
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * true can get in time false can't get in time
	 */
	public boolean goodTravelTime(Route routeA, Route routeB) {
		int endT = routeA.getEndT();
		int startT = routeB.getStartT();
		int timeNeeded = Math.abs(endT - startT);

		int[] loc1 = routeA.getEndL();
		int[] loc2 = routeB.getStartL();
		int dist = calDist(loc1[0], loc1[1], loc2[0], loc2[1]);

		return timeNeeded >= dist;
	}

	// Priority 2 only
	// Goes through deRoute and finds a place where the route could be inserted
	// Doesn't take distance into account
	public int findGoodTRoute(int bt1, int bt2, int distance) {
		int num = -1;
		
		int dist = bt2-distance-bt1;
		//System.out.println(dist+10);
		///System.out.println("BA");
		for (int i = 0; i < deRoute.size(); i++) {
			int at2 = deRoute.get(i).getEndT();
			//System.out.println(at2);
			//System.out.println(bt1+dist + "\n");
			if (at2 <= bt1 + dist) { //dist is added so "outside time" is not needed, look down appendix a
				if (i + 1 == deRoute.size()) {
					num = i;
					break;
				} else {
					if (bt2 <= deRoute.get(i + 1).getStartT()) { //ct1
						num = i;
						break;
					}
				}
			}
		}
		///System.out.println("BE");
		return num;
	}

	// Found in MainChrono
	public static int calDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y2 - y1);
	}

	public ArrayList<Route> getRoutes() {
		return deRoute;
	}

	/*
	 * Not needed
	 * 
	 * public boolean insertRouteDeadAAA(Route routeB) { ///System.out.println("AA");
	 * int startT = routeB.getStartT(); int endT = routeB.getEndT();
	 * 
	 * int loc = findGoodTRoute(startT, endT);
	 * 
	 * ///System.out.println(loc); if (loc != -1) {
	 * ///System.out.println("SHOULD BE ADDING"); Route routeA = deRoute.get(loc);
	 * 
	 * ///System.out.println("AB"); if (goodTravelTime(routeA, routeB)) { // Priority2
	 * doesn't work by design if (loc + 1 == deRoute.size()) { deRoute.add(routeB);
	 * ///System.out.println("AC, ADDED"); return true; } else { Route routeC =
	 * deRoute.get(loc + 1); if (goodTravelTime(routeB, routeC)) { deRoute.add(loc +
	 * 1, routeB); return true; } return false; } } else { return false; } } else {
	 * ///System.out.println("ASDFASDF-1"); return false; } }
	 * 
	 * public void addRouteVoid(Route route) { int num =
	 * findGoodTRoute(route.getStartT(), route.getEndT()); deRoute.add(num+1,
	 * route);
	 * 
	 * /* for (int i = 0; i < range.size(); i++) { if (range.get(i)[1] >
	 * route.getStartT()) { range.add(i+1, new Integer[]
	 * {route.getStartT(),route.getEndT()}); break; } } // * / }
	 * 
	 * public void addRouteEnd(Route route) { Route lastPos =
	 * deRoute.get(deRoute.size() - 1); int[] endxy = lastPos.getEndL();
	 * 
	 * int[] startxy = route.getStartL();
	 * 
	 * if (Arrays.equals(endxy, startxy)) {
	 * 
	 * } deRoute.add(route); }
	 * 
	 * 
	 * //Not done public boolean possibleBefore(Route prevPos) { return true; }
	 */
}

/**
 * Appendix A
 * Example two routes: 
 * aT = [0, 9], aDist = 5
 * bT = [0, 9], aDist = 5
 * (similar to example)
 * 
 * route A takes space from 0, 4
 * and route B can take space 5, 9
 */
