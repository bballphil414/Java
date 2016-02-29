import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.JButton;


public class Point {
	boolean hit = false;
	int _x;
	int _y;
	JButton thisButton;
	JButton opButton;
	View v;
	
	public Point(int x, int y) {
		_x = x;
		_y = y;
	}
	
	public Point(int x, int y, View _v) {
		_x = x;
		_y = y;
		v = _v;
		thisButton = new JButton(" ");
		thisButton.addActionListener(new
	            ActionListener()
	            {
	               public void actionPerformed(ActionEvent event)
	               {
	                 
	               }
	            });
		opButton = new JButton(" ");
		opButton.addActionListener(new
		 ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
              // Do nothing if not our turn!
            	if(checkTurn()) {
            	hit();
            	print();
            	changePoint();
            	changeTurn();
            	} else {
            		System.out.println("Not your turn!");
            	}
            }
         });
	}
		
	private void changePoint() {
		if(v.checkShips(this)) {
    		changeLabelOp("X");
    		changeLabel("*");
    		opButton.setBackground(Color.green);
    		thisButton.setBackground(Color.red);
		} else {
			changeLabelOp("O");
			changeLabel("O");
		}
	}
	
	private boolean checkTurn() {
		return v.checkTurn();
	}
	
	private void changeTurn() {
		v.changeTurn();
	}
	
	public void changeLabel(String s) {
		thisButton.setText(s);
	}
	
	public void print() {
		System.out.println("SHIP: " + _x + "," + _y);
	}
	public void changeLabelOp(String s) {
		opButton.setText(s);
	}
	
	public JButton getButton() {
		return thisButton;
	}
	
	public JButton getOpButton() {
		return opButton;
	}
	
	public void hit() {
		hit = true;
		System.out.println("Orientation: "+_x+","+_y);
	}
	
	public boolean checkHit() {
		return hit;
	}
}
