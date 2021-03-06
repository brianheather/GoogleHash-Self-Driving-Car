package application;

/**
 * 
 */
public class Route {

	private int x1, y1, x2, y2, t1, t2, pos; // pos is which route
	private int dist = -1;

	//Priority 1
	public Route(int x1, int y1, int x2, int y2, int t1, int t2, int pos) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.t1 = t1;
		this.t2 = t2;
		this.pos = pos;
	}
	
	//Priority 2
	public Route(int x1, int y1, int x2, int y2, int t1, int t2, int dist, int pos) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.t1 = t1;
		this.t2 = t2;
		this.dist = dist;
		this.pos = pos;
	}

	public int[] getData() {
		return new int[] { x1, y1, x2, y2, t1, t2, pos };
	}

	public int[] getStartL() {
		return new int[] { x1, y1 };
	}

	public int[] getEndL() {
		return new int[] { x2, y2 };
	}

	public int getStartT() {
		return t1;
	}

	public int getEndT() {
		return t2;
	}

	public int[] getT() {
		return new int[] {t1, t2};
	}
	
	public int getDist() {
		return dist;
	}
	
	public int getPos() {
		return pos;
	}
	
	public void setT(int t1, int t2) {
		this.t1 = t1;
		this.t2 = t2;
	}
}
