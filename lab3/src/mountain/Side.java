package mountain;

public class Side {
	private Point mitt;
	private Point p1;
	private Point p2;

	public Side(Point p1, Point p2, Point mitt){
		// TODO Auto-generated constructor stub
		this.mitt = mitt;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point getMitt(){
		return mitt;
	}
	
	public Point getP1() {
		return p1;
	}
	public Point getP2() {
		return p2;
	}

}
