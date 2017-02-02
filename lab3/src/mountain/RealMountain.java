package mountain;

import java.util.ArrayList;
import java.util.ListIterator;

import fractal.*;

public class RealMountain extends Fractal {
	private Point[] dots;
	private double dev;
	private ArrayList<Side> sides;
	
	public RealMountain(double dev, Point a, Point b, Point c){
		super();
		dots = new Point[3];
		this.dev = dev;
		
		dots[0] = a;
		dots[1] = b;
		dots[2] = c;
		
		sides = new ArrayList<Side>();
	}
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Real Mountain";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		// TODO Auto-generated method stub
		fractalLine(turtle, order, dev, dots[0], dots[1], dots[2]);
	}
	
	private Point getMid(Point a, Point b){
		
		Point ab = new Point(
				(Math.abs(a.getX() + b.getX())) /2 , (Math.abs(a.getY() + b.getY())) /2 + RandomUtilities.randFunc(dev));;
		
		boolean exists = false;
		ListIterator<Side> itr = sides.listIterator();
		
		while(itr.hasNext() && !exists){
			
			Side s = itr.next();
			if(s.getP1().equals(a) && s.getP2().equals(b)){
				ab = s.getMitt();
				itr.remove();
				exists = true;
			}
		}
		if(!exists)
			sides.add(new Side(a,b,ab));
		
		return ab;
	}
	
	private void fractalLine(TurtleGraphics turtle, int order, double dev,  Point a, Point b, Point c){
		if(order == 0){
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		}
		else {
			Point ab, bc, ac;
			
			ab = getMid(a,b);
			bc = getMid(b,c);
			ac = getMid(a,c);
			
			fractalLine(turtle, order-1, dev/2, a, ab, ac);
			fractalLine(turtle, order-1, dev/2, ab, b, bc);
			fractalLine(turtle, order-1, dev/2, ac, bc, c);
			fractalLine(turtle, order-1, dev/2, ab, ac, bc);
		}
	}
	
}
