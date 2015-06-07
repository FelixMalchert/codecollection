
import javax.swing.JFrame;

public class Canvas extends JFrame{

	public Canvas() { 
		super("Canvas");
		this.setSize(550, 550);
		this.setLocationRelativeTo (null);
		this.add(new Surface()); this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE ); 
		this.setLayout (null);
	}

	public static void main(String[] args){
		Canvas canvas = new Canvas(); 
		canvas.setVisible (true);
	}
}