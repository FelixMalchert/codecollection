import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Graphics2D ;
import java.util.Random;
import javax.swing.JPanel;

public class Surface extends JPanel{

	private int width; private int height;
	public Surface(){ 
		this.setBackground (Color.WHITE); 
		this.setSize(500,500);
	}
	@Override 
	public void paintComponent (Graphics g){
		super.paintComponent (g);
		width = this.getWidth();
		height = this.getHeight ();
		System.out.println(width);
		System.out.println(height);
		Graphics2D gr =(Graphics2D ) g;

		gr.setColor(Color.BLACK);
		/*gr.drawLine(width-475, 
		 * height-100, width-25, height-100); 
		 * gr.drawLine(width-475, height-100, (25+450)/2, height-450);
		 * gr.drawLine(width-25, height-100, (25+450)/2, height-450);

	   gr.drawLine((width-100)/2, (height-100)/2, (((25+450)/2)/2)+(((25+450)/2)/2), (350/2));
	   //gr.drawLine(x1, y1, x2, y2);
		 * //gr.drawLine(x1, y1, x2, y2);
		 */ 
		paintRecursive (gr, 150,170,150); 
	}

	public void paintRecursive (Graphics2D gr, int x, int y, int lineSize){
		if(lineSize < 1){
			return; 
		} 
		Random colorNumber = new Random();
		int lineSizeNew = lineSize/2;
		paintRecursive (gr, x, y-lineSize+lineSizeNew , lineSizeNew ); //Top triangle.

		paintRecursive (gr, x-lineSize+lineSizeNew , y+lineSizeNew /2, lineSizeNew ); //Left triangle.

		paintRecursive (gr, x+lineSize-lineSizeNew , y+lineSizeNew /2, lineSizeNew ); //Right triangle. 

		Color color = new Color(colorNumber .nextInt(255), colorNumber .nextInt(255), 
				colorNumber .nextInt(255));
		gr.setColor(color);
		//Bottom Line.
		gr.drawLine(x-lineSize, y+lineSize/2, x+lineSize, y+lineSize/2);
		//Left Line.
		gr.drawLine(x-lineSize, y+lineSize/2, x, y-lineSize);
		//Right Line. 
		gr.drawLine(x+lineSize, y+lineSize/2, x, y-lineSize);
	}
}
