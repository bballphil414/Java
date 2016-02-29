
public class TNode {

	TNode childLeft;
	TNode childRight;
	String type;
	String content;
	
	// Type is either Celebrity or Question
	
	public TNode(String s, String c) {
		type = s;
		content = c;
	}
	
	public void setString(String s) {
		content = s;
	}
	
	public void setType(String s) {
		type = s;
	}
	
	public String getString() {
		return content;
	}
	
	public String getType() {
		return type;
	}
	
	public void setLeft(TNode n) {
		childLeft = n;
	}
	
	public void setRight(TNode n) {
		childRight = n;
	}
	
	public TNode getLeft() {
		return childLeft;
	}
	
	public TNode getRight() {
		return childRight;
	}
}
