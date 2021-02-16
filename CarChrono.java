/**
 * Variable range removed, might add back to speed up searching for start and end journey times
 * Combined checkRoute and addRoute -->method: insertRoute
 */
package application;

import java.util.ArrayList;
//import java.util.Arrays;

public class CarChrono {

	// private ArrayList<Integer[]> range = new ArrayList<>();
	private ArrayList<Route> deRoute = new ArrayList<>();

	public CarChrono() {
		// range.add(new Integer[] {0});
		deRoute.add(new Route(0, 0, 0, 0, 0, 0, 0));
	}

	public boolean insertRoute(Route routeB) {
		System.out.println("AA");
		int startT = routeB.getStartT();
		int endT = routeB.getEndT();

		int loc = findGoodTRoute(startT, endT);

		System.out.println(loc);
		if (loc != -1) {
			System.out.println("SHOULD BE ADDING");
			Route routeA = deRoute.get(loc);
	
			System.out.println("AB");
			if (goodTravelTime(routeA, routeB)) {
				if (loc + 1 == deRoute.size()) {
					deRoute.add(routeB);
					System.out.println("AC, ADDED");
					return true;
				} else {
					Route routeC = deRoute.get(loc + 1);
					if (goodTravelTime(routeB, routeC)) {
						deRoute.add(loc + 1, routeB);
						return true;
					}
					return false;
				} 
			} else {
				return false;
			}
		} else {
			System.out.println("ASDFASDF-1");
			return false;
		}
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

	//Goes through deRoute and finds a place where the route could be inserted
	//Doesn't take distance into account
	public int findGoodTRoute(int startT, int endT) {
		int num = -1;
		
		System.out.println("BA");
		for (int i = 0; i < deRoute.size(); i++) {
			System.out.println("BB");
			Route elem = deRoute.get(i);
			if (elem.getEndT() <= startT) {
				if (i == deRoute.size() - 1) {
					System.out.println("BC");
					num = i;
				} else {
					if (endT <= deRoute.get(i + 1).getStartT()) {
						System.out.println("BD");
						num = i;
						break;
					}
				}

			}
		}
		System.out.println("BE");
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
	 * Not needed public void addRouteVoid(Route route) { int num =
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
