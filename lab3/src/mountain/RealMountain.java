package mountain;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.stream.IntStream;

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
	
	
	private Point[] getMid(Point[] triPoint){
		
		Point[] points = new Point[3];
						
		int[] counter = {0,0,0};
		
		ListIterator<Side> itr = sides.listIterator();
		
		while(itr.hasNext() && IntStream.of(counter).sum() < 3){
			
			Side s = itr.next();
			if(s.getP1().equals(triPoint[0]) && s.getP2().equals(triPoint[1])){
				points[0] = s.getMitt();
				itr.remove();
				counter[0] = 1;
			}
			else if(s.getP1().equals(triPoint[1]) && s.getP2().equals(triPoint[2])) {
				points[1] = s.getMitt();
				itr.remove();
				counter[1] = 1;
			}
			else if(s.getP1().equals(triPoint[0]) && s.getP2().equals(triPoint[2])){
				points[2] = s.getMitt();
				itr.remove();
				counter[2] = 1;
			}
		}
		
//		for(int i = 0; i < counter.length; i++){
//			int j = 0,k = 0;
//			if(i == 0 && counter[1] == 0){
//				j = 0;
//				k = 1;
//			}
//			else if( i==1 && counter[1] == 0){
//				j = 1;
//				k = 2;
//			}
//			else if(i == 2 && counter[2] == 0){
//				j = 0;
//				k = 2;
//			}
//			points[i] = new Point(
//					(Math.abs(triPoint[j].getX() + triPoint[k].getX())) /2 , 
//					(Math.abs(triPoint[j].getY() + triPoint[k].getY())) /2 + RandomUtilities.randFunc(dev));
//			sides.add(new Side(triPoint[j], triPoint[k], points[i]));
//		}
		
		if(counter[0] == 0){
			points[0] = new Point(
					(Math.abs(triPoint[0].getX() + triPoint[1].getX())) /2 , 
					(Math.abs(triPoint[0].getY() + triPoint[1].getY())) /2 + RandomUtilities.randFunc(dev));
			sides.add(new Side(triPoint[0],triPoint[1],points[0]));
		}
		if(counter[1] == 0){
			points[1] = new Point(
					(Math.abs(triPoint[1].getX() + triPoint[2].getX())) /2 , 
					(Math.abs(triPoint[1].getY() + triPoint[2].getY())) /2 + RandomUtilities.randFunc(dev));
			sides.add(new Side(triPoint[1],triPoint[2],points[1]));
		}
		if(counter[2] == 0){
			points[2] = new Point(
					(Math.abs(triPoint[0].getX() + triPoint[2].getX())) /2 , 
					(Math.abs(triPoint[0].getY() + triPoint[2].getY())) /2 + RandomUtilities.randFunc(dev));
			sides.add(new Side(triPoint[0],triPoint[2],points[2]));
		}
			
		
		return points;
	}
	
	private void fractalLine(TurtleGraphics turtle, int order, double dev,  Point a, Point b, Point c){
		if(order == 0){
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		}
		else {
			Point[] p = {a,b,c};
			
			Point[] midPoints = getMid(p);
			
			fractalLine(turtle, order-1, dev/2, a, midPoints[0], midPoints[2]);
			fractalLine(turtle, order-1, dev/2, midPoints[0], b, midPoints[1]);
			fractalLine(turtle, order-1, dev/2, midPoints[2], midPoints[1], c);
			fractalLine(turtle, order-1, dev/2, midPoints[0], midPoints[2], midPoints[1]);
		}
	}
	
}
