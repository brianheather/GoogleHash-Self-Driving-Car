package application;
import java.util.ArrayList;

public class CarChrono {

	/*range [0,[t1, t2]]
	 * 0 can be 1, 0 is travelling to location, 1 is carrying passenger
	 */
	
	private ArrayList<Integer[]> range = new ArrayList<>();
	private ArrayList<Route> deRoute = new ArrayList<>();
	
	public CarChrono() {
		deRoute.add(new Route(0,0,0,0,0,0,0));
	}
	
	/* true can get in time
	 * false can't get in time 
	 */
	public boolean possibleEnd(Route nextPos) {
		Route lastPos = deRoute.get(deRoute.size() - 1);
		int[] endxy = lastPos.getEndL();
		int endt = lastPos.getEndT();
		
		int[] startxy = nextPos.getStartL();
		int startt = nextPos.getStartT();
		
		int dist = calDist(endxy[0], endxy[1], startxy[0], startxy[1]);
		int time = startt - endt;
		
		return dist <= time; //priority1 false not good, priority2 false is ok
	}
	
	public boolean possibleBefore(Route prevPos) {
		
		return true;
	}
	
	//Found in MainChrono
	public static int calDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y2 - y1);
	}

}
