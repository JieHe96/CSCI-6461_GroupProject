import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.*;

public class MainApp 
{
	public static void main (String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				JFrame frame = new MainFrame("Computer Simulator");
				frame.setSize(800, 400);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
	}

}
