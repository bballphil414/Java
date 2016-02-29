import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

public class GridGame {
	ArrayList<JComponent> panels = new ArrayList<JComponent>();	
	JFrame f = new JFrame();
	ArrayList<Button> buttonList = new ArrayList<Button>();
	String _name;
	
	
	public GridGame(int rows, int columns, String name) {
		// Create the grid game
		_name = name;
		
		JPanel gamePanel = new JPanel();
	    gamePanel.setLayout(new GridLayout(rows, columns));
	      
	    for(Button p : buttonList) {		    	  
	    	gamePanel.add(p.getButton());
	    }
	    DrawGrid d = new DrawGrid(5);
	    f.add(gamePanel);
	    f.add(d);
	    f.setSize(rows*50, columns*50);
	}
	
	public void play() {
		 f.setVisible(true);
		 f.setTitle(_name);
	}
}
