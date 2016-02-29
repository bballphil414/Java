import java.util.ArrayList;


public class GameManager {
	Window one;
	Window two;
	
	View v_one;
	View v_two;
	
	int turn = 0;
	
	public GameManager() {
		one = new Window("Phil", this, 0);
		two = new Window("John", this, 1);
	}
	
	public void play() {
		v_one = new View(one, two);
		v_two = new View(two, one);
		
		one.setViews(v_one, v_two);
		two.setViews(v_two, v_one);
		
		one.start();
		two.start();
	}
	
	public void startGame() {
		v_one.setShips(getPlayerOneShips());
		v_two.setShips(getPlayerTwoShips());
		
		one.startGame();
		two.startGame();
	}
	
	public ArrayList<Ship> getPlayerOneShips() {
		ArrayList<Ship> myShips = new ArrayList<Ship>();
		// View Layout for player one (not randomely generated)
				 /*
				  *   0 1 2 3 4 5 6 7 8 9 10
				  * 0 x
				  * 1 x   x x
				  * 2 x   
				  * 3
				  * 4           x
				  * 5           x
				  * 6           x
				  * 7           x
				  * 8
				  * 9
				  * 10            x x x x x
				  * 
				 */
				 
				 ArrayList<Point> sp1 = new ArrayList<Point>();
				 sp1.add(new Point(0,0));
				 sp1.add(new Point(0,1));
				 sp1.add(new Point(0,2));
				 Ship s1 = new Ship(sp1);
				 
				 ArrayList<Point> sp2 = new ArrayList<Point>();
				 sp2.add(new Point(2,1));
				 sp2.add(new Point(3,1));
				 Ship s2 = new Ship(sp2);
				 
				 ArrayList<Point> sp3 = new ArrayList<Point>();
				 sp3.add(new Point(5,4));
				 sp3.add(new Point(5,5));
				 sp3.add(new Point(5,6));
				 sp3.add(new Point(5,7));
				 Ship s3 = new Ship(sp3);
				 
				 ArrayList<Point> sp4 = new ArrayList<Point>();
				 sp4.add(new Point(6,10));
				 sp4.add(new Point(7,10));
				 sp4.add(new Point(8,10));
				 sp4.add(new Point(9,10));
				 sp4.add(new Point(10,10));
				 Ship s4 = new Ship(sp4);
				 
				 myShips.add(s1);
				 myShips.add(s2);
				 myShips.add(s3);
				 myShips.add(s4);
				 return myShips;
	}
	
	public ArrayList<Ship> getPlayerTwoShips() {
		ArrayList<Ship> myShips = new ArrayList<Ship>();
		// View Layout for player one (not randomely generated)
				 /*
				  *   0 1 2 3 4 5 6 7 8 9 10
				  * 0 x
				  * 1 x   x x
				  * 2 x   
				  * 3
				  * 4           x
				  * 5           x
				  * 6           x
				  * 7           x
				  * 8
				  * 9
				  * 10            x x x x x
				  * 
				 */
				 
				 ArrayList<Point> sp1 = new ArrayList<Point>();
				 sp1.add(new Point(0,0));
				 sp1.add(new Point(0,1));
				 sp1.add(new Point(0,2));
				 Ship s1 = new Ship(sp1);
				 
				 ArrayList<Point> sp2 = new ArrayList<Point>();
				 sp2.add(new Point(2,1));
				 sp2.add(new Point(3,1));
				 Ship s2 = new Ship(sp2);
				 
				 ArrayList<Point> sp3 = new ArrayList<Point>();
				 sp3.add(new Point(5,4));
				 sp3.add(new Point(5,5));
				 sp3.add(new Point(5,6));
				 sp3.add(new Point(5,7));
				 Ship s3 = new Ship(sp3);
				 
				 ArrayList<Point> sp4 = new ArrayList<Point>();
				 sp4.add(new Point(6,10));
				 sp4.add(new Point(7,10));
				 sp4.add(new Point(8,10));
				 sp4.add(new Point(9,10));
				 sp4.add(new Point(10,10));
				 Ship s4 = new Ship(sp4);
				 
				 myShips.add(s1);
				 myShips.add(s2);
				 myShips.add(s3);
				 myShips.add(s4);
				 return myShips;
	}
	
	public int changeTurn() {
		if(turn == 0) {
			turn = 1;
		} else {
			turn = 0;
		}
		return turn;
	}
}
