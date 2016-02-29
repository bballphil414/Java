import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class Button {
	boolean hit = false;
	int _x;
	int _y;
	JButton thisButton;
	GameInterface g;
	
	public Button(int x, int y, GameInterface _g) {
		_x = x;
		_y = y;
		g = _g;
		thisButton = new JButton(" ");
		thisButton.addActionListener(new
	            ActionListener()
	            {
	               public void actionPerformed(ActionEvent event)
	               {
	            	   hit();
	               	   print();
	               	   g.onButtonClick(getButtonObj());
	               }
	            });
	}
	
	public Button getButtonObj() {
		return this;
	}
	
	public void changeLabel(String s) {
		thisButton.setText(s);
	}
	
	public void print() {
		System.out.println("Print POINT: " + _x + "," + _y);
	}
	
	public JButton getButton() {
		return thisButton;
	}
	
	public void hit() {
		hit = true;
		System.out.println("Orientation: "+_x+","+_y);
	}
	
	public boolean checkHit() {
		return hit;
	}
}
