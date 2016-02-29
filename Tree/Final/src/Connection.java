import java.net.*;
import java.io.*;
import java.util.*;

public class Connection implements Runnable {
 
	Socket connection;
	int port = 6001;
	
	TNodeList tempList;
	BufferedReader is;
	InputStreamReader isr;
	StringBuffer process = new StringBuffer();
	DataOutputStream osw;
	
	private final Timer myTimer = new Timer();
	public static ArrayList<Celeb> curCelebList = new ArrayList<Celeb>();
	public static ArrayList<Connection> connectionList = new ArrayList<Connection>();
	
	private final int ID;
 
	public static void closeConnection(Connection c) {
		c = null;
	}
	
	Connection(int id, TNodeList l, Socket s) {
		tempList = l;
		ID = id;
		connection = s;
		connectionList.add(this);
	}
	
	
	
	Connection(int id, TNodeList l) {
		tempList = l;
		ID = id;
		
		// create the original server socket
		
		try {
			ServerSocket socket = new ServerSocket(port);
			while (true) {
				System.out.println("Starting thread");
				Runnable runnable = new Connection(++id, l, socket.accept());
				Thread thread = new Thread(runnable);
				
				thread.start();
			}
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("ERROR");
		}
	}

	public void addCeleb(String s, int ID) {
		curCelebList.add(new Celeb(s, ID));
		System.out.println("Added " + s + " celebrity to the list.");
	}
	
	public void removeCeleb(int ID) {
		for(Celeb a : curCelebList) {
			if(a._ID == ID) {
				curCelebList.remove(a);
			}
		}
		
		// send a notification to the creator that this Connection is no longer valid
		Connection.closeConnection(this);
	}
	
	public static void checkCeleb(Celeb c, int curID) {
		System.out.println("Checking celeb " + c.string + " with curID " + curID);
		for(Connection z : connectionList) {
			for(Celeb x : curCelebList) {
			if(x._ID == z.getID() && x._ID != curID && x.string.equals(c.string)) {
				z.print("The user with ID # " + c._ID + " chose the same celebrity ('" + c.string + "') as you!");
			}
			}
		}
	}
	
	public int getID() {
		return ID;
	}
	
	public void print(String s) {
		try {
			osw.writeUTF(s + "\n");
			
		} catch(Exception e) {
			System.out.println(e);
			removeCeleb(ID);
		}
	}
	
	public String read() {
		String s = "";
		try {
			s = this.is.readLine();
		} catch(Exception e) {
			System.out.println(e);
			removeCeleb(ID);
		}
		return s;
	}
 
	public void run() {
		System.out.println("New Connection created");
		
		
		try {
			// write first
			isr = new InputStreamReader(connection.getInputStream());
			is = new BufferedReader(isr);
			
			osw =new DataOutputStream(connection.getOutputStream());
			
			
			// do the questions, yes go left, no go right, if last is celebrity quit.
			this.print("Welcome to the server.");
			
			
			System.out.println("Sent message to client.");
			
			this.askQuestion(tempList.getRoot());
				
		} catch (IOException e) {
			System.out.println("ERROR1");
			removeCeleb(ID);
		} catch (Exception e) {
			System.out.println("ERROR2");
			removeCeleb(ID);
		} finally {
			try {
				this.connection.close();
			} catch (IOException e) {
				System.out.println("ERROR3");
				removeCeleb(ID);
			}
		}
	
	}
	
	public void askQuestion(TNode n) {
		// Get the string of the Node for the question
		String s = n.getString();
		
		// Ask the question
		this.print(s);

		// get the input (yes or no)
		
		String input = "";
		
		input = read();
		
		if(input.equalsIgnoreCase("yes")) {
			// read the left child.  If no left child, then this question is finished and the game is over.
			if(n.getLeft() != null) {
				this.askQuestion(n.getLeft());
			} else {
				String result = "I'm so smart!\n";
				print(result);
				// No saving required here since the server was correct.
				
				this.restart();
			}
		} else {
			// read the right child. (input is no).  if no right child, ask who the celebrity is.  otherwise, ask the question.
			if(n.getRight() != null) {
				this.askQuestion(n.getRight());
			} else {
				String result = "Who is your celebrity?\n";
				print(result);
				// get the celebrity
				
				input = read();
				
				this.addCeleb(input, ID);
				Connection.checkCeleb(new Celeb(input, ID), ID);
				System.out.println("Checking celeb");
				
				TNode x = new TNode("celebrity", input);
				
				result = "Please enter a question that would differentiate between " + n.getString() + " and " + x.getString() + "\n";
				print(result);
				
				input = read();
				
				TNode q = new TNode("question", input);
				
				result = "Would an answer of yes indicate " + x.getString() + "?\n";
				print(result);
				
				input = "";
				
				// swap the previous celebrity answer try and this question so it gets asked to differentiate
				String prevName = n.getString();
				String prevType = n.getType();
				
				synchronized(n) {
				n.setString(q.getString());
				n.setType(q.getType());
				
				q.setString(prevName);
				q.setType(prevType);
				
				input = read();
				
				if(input.equalsIgnoreCase("yes")) {
					n.setLeft(x);
					n.setRight(q);
				} else {
					n.setRight(x);
					n.setLeft(q);
				}
				}
				
				// swap the node info for the celebrity in question and the question previously asked
				
				result = "Thank you for adding " + x.getString() + " to the database.\n";
				print(result);
				
				// Save the new tree, only here since we shouldn't read in all the time or write all the time.
				Saver.save(this.tempList);
				
				this.restart();
			}
		}
		
		
	}
	
	private void restart() {
		this.askQuestion(tempList.getRoot());
	}
}