import javax.swing.JComponent;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
	 
public class DrawGrid extends JComponent
{
	private int side;
	public DrawGrid(int n)
	{
		side = n;
	    setPreferredSize(new Dimension(1000, 1000));
	 
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
	    int count = side;
	    int size = 40;
	     
	    for( int i = 0; i < count; i ++)
	    {
	    	for( int j = 0; j < count; j++)
	        {
	    		Rectangle grid = new Rectangle( 300 + i * size, 20 + j * size, size, size); 
	            g2.draw(grid);
	        }
	    }
	}
}
	  