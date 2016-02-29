import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.*;

public class View {
	Window one;
	Window two;
	
	JPanel myView = new JPanel();
	JPanel opView = new JPanel();
	
	ArrayList<Ship> myShips = new ArrayList<Ship>();
	ArrayList<Point> pointList = new ArrayList<Point>();
	
	public View(Window _one, Window _two) {
		 one = _one;
		 two = _two;
		 
		 // Prepare My Game View Label [for use on my window]
		 myView.setLayout(new BorderLayout());
		 myView.add(new JLabel("^ " + two.getName() + "'s Game Panel ^\n\n"), BorderLayout.CENTER);
		 
		 // Prepare Opponent Game View Label [for use on opponent's window]
		 //opView.setLayout(new BorderLayout());
		// opView.add(new JLabel(two.getName() + "'s Screen\n\n"), BorderLayout.NORTH);
		 
		 for (int i = 0; i <= 10; i++)
	      {
	    	  for(int i2 = 0; i2 <= 10; i2++) {
	    		  Point p = new Point(i, i2, this);
	    		  pointList.add(p);
	    	  }
	      }
	}
	
	public void setShips(ArrayList<Ship> list) {
		myShips = list;
	}
	
	public JPanel getMyView() {
		return myView;
	}
	
	public JPanel getOpView() {
		return opView;
	}
	
	public JPanel getGameView() {
	      JPanel gamePanel = new JPanel();
	      gamePanel.setLayout(new GridLayout(12, 5));
	      
	      for(Point p : pointList) {	
	    	  if(checkShips(p)) {
	    		  p.changeLabel("x");
	    		  p.print();
	    	  }
	    	  
	    	  gamePanel.add(p.getButton());
	      }
	      
	      return gamePanel;
	}
	
	public JPanel getOpGameView() {
		  JPanel gamePanel = new JPanel();
	      gamePanel.setLayout(new GridLayout(12, 5));
	      
	      for(Point p : pointList) {
	    	  gamePanel.add(p.getOpButton());
	      }
	      
	      return gamePanel;
	}
	
	public boolean checkShips(Point p) {
		boolean c = false;
		for(Ship s : myShips) {
			if(s.checkShip(p)) {
				c = true;
			}
		}
		return c;
	}
	
	public boolean checkTurn() {
		return one.checkTurn();
	}
	
	public void changeTurn() {
		for(Ship s : myShips) {
			if(!s.checkSunk()) {
				System.out.println("..");
				one.changeTurn();
				return;
			}
		}
		// Got through the loop, this player has lost!
		one.lostGame();
	}
	
}
