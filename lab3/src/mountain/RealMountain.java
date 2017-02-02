package mountain;

import java.util.ArrayList;
import java.util.Iterator;

import fractal.*;

public class RealMountain extends Fractal {
	Point[] dots;
	double dev;
	ArrayList<Side> sides;
	
	public RealMountain(double dev, Point a, Point b, Point c){
		super();
		dots = new Point[3];
		this.dev = dev;
		
		dots[0] = a;
		dots[1] = b;
		dots[2] = c;
		
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
					(Math.abs(a.getX() + b.getX())) /2 , (Math.abs(a.getY() + b.getY())) /2);
			Point bc = new Point(
					(Math.abs(b.getX() + c.getX())) /2 , (Math.abs(b.getY() + c.getY())) /2);
			Point ac = new Point( 
					(Math.abs(a.getX() + c.getX())) /2 , (Math.abs(a.getY() + c.getY())) /2);
			
			Iterator<Side> itr = sides.iterator();
			
			while (itr.hasNext()){
				Side s = itr.next();
				
				if(!s.getMitt().equals(ab) && !s.getP1().equals(a) && !s.getP2().equals(b)){
					ab.move(ab.getX(), ab.getY() + dev);
					sides.add(new Side(a, b, ab));
				}else {
					sides.remove(s);
				}
				
				if(!s.getMitt().equals(bc) && !s.getP1().equals(b) && !s.getP2().equals(c)){
					bc.move(bc.getX(), bc.getY() + dev);
					sides.add(new Side(b, c, bc));
				}else {
					sides.remove(s);
				}
				
				if(!s.getMitt().equals(ac) && !s.getP1().equals(a) && !s.getP2().equals(c)){
					ac.move(ac.getX(), ac.getY() + dev);
					sides.add(new Side(a, c, ac));
				}else {
					sides.remove(s);
				}
			}
				
			
			
			fractalLine(turtle, order-1, dev/2, a, ab, ac);
			fractalLine(turtle, order-1, dev/2, ab, b, bc);
			fractalLine(turtle, order-1, dev/2, ac, bc, c);
			fractalLine(turtle, order-1, dev/2, ab, ac, bc);
		}
	}
	
}
