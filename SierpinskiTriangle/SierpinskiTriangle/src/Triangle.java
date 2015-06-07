
import java.awt.*;
import java.io.IOException ;
import java.util.Random;
import javax.swing.JFrame;

public class Triangle extends JFrame{

	private static final long serialVersionUID = 1L;
	Random r = new Random();
	JFrame window;

	public Triangle () { 
		/** * This sets up the window for drawing our triangle including
		 *   * setting a window title and getting the size of the screen in use 
		 *   */ 
		super();
		setTitle("Sierpinski Triangle" );
		setSize(Toolkit.getDefaultToolkit ().getScreenSize ());
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE );
		setBackground (Color.WHITE);
		setVisible (true);
	}

	public void paint(Graphics g){
		Dimension dim = this.getSize();

		/** 
		 * Setting up a margin from the window frame to the triangle 
		 *points so that it looks nicer 
		 */ 
		int margin = 50;

		/** 
		 * Depending on whether the screen width is greater than the  
		 * screen height this uses if-else to cover both cases 
		 */

		Point a,b,c;
		if(dim.width > dim.height){
			int height = dim.height - margin;
			double length = 2 * height / Math.sqrt(3);


			a = new Point((int)(dim.width/2 - length/2), dim.height - margin/2);
			b = new Point((int)(dim.width/2 + length/2), dim.height - margin/2);
			c = new Point(dim.width/2, margin/2);

			drawTriangle (a,b,c,g);

		}else{ 
			int length = dim.width - margin;
			int height =(int)(Math.sqrt(3)*length/2);

			a = new Point (margin/2, dim.height - margin/2);
			b = new Point (dim.width - margin/2, dim.height - margin/2);
			c = new Point (dim.width/2, dim.height - margin/2 - height);

			drawTriangle (a,b,c,g);

		}

		drawAllOtherTriangles (a,b,c,g);
	}

	/* 
	 * Daraws a Triangle from three given Points to a Graphics panel 
	 */ 

	private void drawTriangle (Point a, Point b, Point c, Graphics g){
		/** To use the fillPolygon method  we need to set up arrays  
		 * for the x and y coordinates.  
		 */ 

		int[] x ={a.x, b.x, c.x };
		int[] y ={a.y, b.y, c.y };
		//Picking a random color for each new triangle
		g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
		g.fillPolygon (x, y, 3);
	}

	private void drawAllOtherTriangles (Point a, Point b, Point c, Graphics g){
		/** 
		 * Our termination condition making it so that once the distance 
		 * between two of the points of a triangle becomes less than 10 
		 * then no more triangles are created 
		 */ 
		if(b.x - a.x < 10){
			return;
		}

		/** 
		 * Set new Points a_ b_ c_ at the medians of the triangle for 
		 * setting up points for the touching triangles 
		 */

		Point a_ = new Point ((a.x + c.x)/2,( a.y + c.y)/2);
		Point b_ = new Point ((c.x + b.x)/2,( c.y + b.y)/2);
		Point c_ = new Point ((b.x + a.x)/2,( b.y + a.y)/2);

		//This draws the outer triangle 
		drawTriangle (a_,b_,c_,g);
		//This draws the inside triangles beginning with the bottom left one 
		drawAllOtherTriangles (a, a_, c_, g);
		//bottom right triangle 
		drawAllOtherTriangles (c_, b_, b, g);
		//top triangle 
		drawAllOtherTriangles (a_, c, b_, g);
	}
	/** 
	 * The main Method 
	 * @param args 
	 * @throws IOException 
	 */ 
	public static void main(String[] args){
		new Triangle();
	}
}
