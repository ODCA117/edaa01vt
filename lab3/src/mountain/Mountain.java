package mountain;
import fractal.*;

public class Mountain extends Fractal {
	Point[] dots;
	
	public Mountain(Point a, Point b, Point c){
		super();
		dots = new Point[3];
		
		dots[0] = a;
		dots[1] = b;
		dots[2] = c;
		
	}
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		// TODO Auto-generated method stub
		fractalLine(turtle, order, dots[0], dots[1], dots[2]);
	}
	
	private void fractalLine(TurtleGraphics turtle, int order, Point a, Point b, Point c){
		if(order == 0){
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		}
		else {
			Point ab = new Point( (Math.abs(a.getX() + b.getX())) /2 , (Math.abs(a.getY() + b.getY())) /2  );
			Point bc = new Point( (Math.abs(b.getX() + c.getX())) /2 , (Math.abs(b.getY() + c.getY())) /2  );
			Point ac = new Point( (Math.abs(a.getX() + c.getX())) /2 , (Math.abs(a.getY() + c.getY())) /2  );
			
			
			fractalLine(turtle, order-1, a, ab, ac);
			fractalLine(turtle, order-1, ab, b, bc);
			fractalLine(turtle, order-1, ac, bc, c);
			fractalLine(turtle, order-1, ab, ac, bc);
		}
	}
}
