import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

import java.util.*;


public class Window {
	View my_view;
	View opponent_view;
	
	int _id;
	
	ArrayList<JComponent> panels = new ArrayList<JComponent>();
	
	GameManager m;
	
	JFrame f = new JFrame();
	
	String _name;
	
	public Window(String name, GameManager _m, int id) {
		_name = name;
		m = _m;
		_id = id;
	}
	
	public void start() {
		this.showWelcomeScreen();
	}
	
	public void setViews(View one, View two) {
		my_view = one;
		opponent_view = two;
	}
	
	public JFrame getFrame() {
		return f;
	}
	
	public String getName() {
		return _name;
	}
	
	private void showWelcomeScreen() {
		// Show the welcome screen
		 JPanel welcome_panel = new JPanel();
	     welcome_panel.setLayout(new BorderLayout());
	     welcome_panel.add(new JLabel("Welcome to BattleShip, " + _name + "!"), BorderLayout.NORTH);
	     JButton goButton = new JButton("Start Game!");
	     f.add(welcome_panel, BorderLayout.NORTH);
	     f.add(goButton);
	     panels.add(welcome_panel);
	     panels.add(goButton);
	     goButton.addActionListener(new
		            ActionListener()
		            {
		               public void actionPerformed(ActionEvent event)
		               {
		                  m.startGame();
		               }
		            });
	     
	     f.pack();
	     f.setVisible(true);
	}
	
	public void startGame() {
		// show op view, opgameview, my view, my game view in order
		f.remove(panels.get(0));
		f.remove(panels.get(1));
		f.add(opponent_view.getOpView(), BorderLayout.WEST);
		f.add(opponent_view.getOpGameView(), BorderLayout.NORTH);
		f.add(my_view.getMyView(), BorderLayout.EAST);
		f.add(my_view.getGameView(), BorderLayout.SOUTH);
		
		f.setSize(800, 800);
		f.setVisible(true);
		f.setTitle("BattleShip: " + _name + "'s Game Panel");
	}
	
	public boolean checkTurn() {
		if(m.turn == _id) {
			return true;
		} else {
			return false;
		}
	}
	
	public void changeTurn() {
		m.changeTurn();
	}
	
	public void lostGame() {
		System.out.println("Player " + _name + " lost the game!");
	}
}
