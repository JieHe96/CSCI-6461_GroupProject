import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Class that loads frame, memory, instruction list, registers and runs this program.
 */
public class MainApp {


	public static MainFrame frame;

	public static Memory myMemory;
	public static InstructionList myInstructionList;
	public static Register myRegisters;
	public static Clock myClock;
	public static Device myDevice;
	public static Cache myCache;
	public static FileLoader myFileLoader;
	
	public static void main (final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			/** Creates mainframe and set size. */
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
		myClock = new Clock();
		myDevice = new Device();
		myCache = new Cache();
		//myFileLoader = new FileLoader();
	}
	
}
