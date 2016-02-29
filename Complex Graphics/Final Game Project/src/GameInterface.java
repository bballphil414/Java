import java.util.*;

public interface GameInterface {
	void onButtonClick(Button b); // action to be performed when button is clicked
	ArrayList<Button> makeButtons(); // create button list
	void play();
}
