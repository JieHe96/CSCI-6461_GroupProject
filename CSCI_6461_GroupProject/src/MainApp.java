import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.IntStream;

public class MainApp {


	public static MainFrame frame;

	public static Memory myMemory;
	public static InstructionList myInstructionList;
	public static Register myRegisters;
	
	
	public static void main (final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				frame = new MainFrame("Computer Simulator");
				frame.setSize(800, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
		
		init();
	}
	
	private static void init() {
		myMemory = new Memory();
		myInstructionList = new InstructionList();
		myRegisters = new Register();
	}
	
}
