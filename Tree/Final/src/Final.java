import java.net.*;
import java.io.*;
import java.util.*;

/**
 * @author Phil
 *
 */
public class Final {

	/**
	 * @param args
	 */
	static int count = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// start base connection
		TNodeList list = Saver.load();
		readStart(list);
		
	}
	
	public static void readStart(TNodeList list) {
		 
		Connection c = new Connection(0, list);
	}

}
