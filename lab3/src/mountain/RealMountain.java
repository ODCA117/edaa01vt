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
	
	private void fractalLine(TurtleGraphics turtle, int order, double dev,  Point a, Point b, Point c){
		if(order == 0){
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		}
		else {
			
			Point ab = new Point(
					(Math.abs(a.getX() + b.getX())) /2 , (Math.abs(a.getY() + b.getY())) /2 + RandomUtilities.randFunc(dev));
			Point bc = new Point(
					(Math.abs(b.getX() + c.getX())) /2 , (Math.abs(b.getY() + c.getY())) /2 + RandomUtilities.randFunc(dev));
			Point ac = new Point( 
					(Math.abs(a.getX() + c.getX())) /2 , (Math.abs(a.getY() + c.getY())) /2 + RandomUtilities.randFunc(dev));
			
			
			if(sides.size() == 0){
				sides.add(new Side(a, b , ab) );
			}
			
			else{
				
				ListIterator<Side> itr = sides.listIterator();
				
				while(itr.hasNext()){
					Side s = itr.next();
					
					if(s.getP1().equals(a) && s.getP2().equals(b)){
						itr.remove();
						ab = s.getMitt();
					}
					else if(s.getP1().equals(b) && s.getP2().equals(c)){
						itr.remove();
						bc = s.getMitt();
					}
					else if(s.getP1().equals(a) && s.getP2().equals(c)){
						itr.remove();
						ac = s.getMitt();
					}
					else{
						itr.add(s);
					}
				}
			}
			
			fractalLine(turtle, order-1, dev/2, a, ab, ac);
			fractalLine(turtle, order-1, dev/2, ab, b, bc);
			fractalLine(turtle, order-1, dev/2, ac, bc, c);
			fractalLine(turtle, order-1, dev/2, ab, ac, bc);
		}
	}
	
}
