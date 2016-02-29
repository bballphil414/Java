import java.util.*;

public class Ship {
	ArrayList<Point> list = new ArrayList<Point>();
	boolean sunk = false;
	
	public Ship(ArrayList<Point> _list) {
		list = _list;
	}
	
	public boolean checkShip(Point x) {
		for(Point p : list) {
			if(p._x == x._x && p._y == x._y) {
				return true;
			}
		}
		return false;
	}
	
	public void hitPoint(Point x) {
		for(Point p : list) {
			if(p._x == x._x && p._y == x._y) {
				p.hit();
			}
		}
	}
	
	public boolean checkSunk() {
		boolean c = true;
		for(Point x : list) {
			if(!x.checkHit()) {
				c = false;
			}
		}
		return c;
	}
}
