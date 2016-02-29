import java.util.*;
public class ConnectFour_Game extends GridGame implements GameInterface {
	
	public ConnectFour_Game(int rows, int columns, String name) { 
		super(rows, columns, name);
	}
	
	public void onButtonClick(Button b) {
		// Do action on button click
	}
	
	public ArrayList<Button> makeButtons() {
		ArrayList<Button> bList = new ArrayList<Button>();
		
		for (int i = 0; i <= 10; i++)
	      {
	    	  for(int i2 = 0; i2 <= 10; i2++) {
	    		  Button p = new Button(i, i2, this);
	    		  bList.add(p);
	    	  }
	      }
		
		return bList;
	}
	
	public void play() {
		super.play();
	}
}
