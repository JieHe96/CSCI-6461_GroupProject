import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MainApp {
	
	public static MainFrame frame;

	public static Memory myMemory;
	public static Instruction myInstructions;
	
	
	public static void main (String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				frame = new MainFrame("Computer Simulator");
				frame.setSize(800, 400);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
		
		init();
	}
	
	private static void init() {
		myMemory = new Memory();
		myInstructions = new Instruction();
	}
	
}
