import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
/** The Mainframe class extends the JFrame class to create the layout of the UI and the various panes 
 * present in the UI */
public class MainFrame extends JFrame{
	
	public UIPanel bottom = new UIPanel(new GridLayout(2,1));
	
	public MainFrame(String title) {
		super(title);
		
		setLayout(new GridLayout(2,1));

		JPanel top = new JPanel(new GridLayout(1,2));
		//JPanel bottom = new JPanel(new GridLayout(2,1));
		
		
		//Instruction Section
		JScrollPane instructionPanel = initInstructionPanel();
		top.add(instructionPanel);
		//Register Section
		JScrollPane registerPanel = initRegisterPanel();
		top.add(registerPanel);
		
		add(top);
		add(bottom);
	}
	// creating instruction pane on the UI
	private JScrollPane initInstructionPanel() {
		JScrollPane instructionPanel = new JScrollPane();
		TitledBorder instructionBorder = new TitledBorder("Instructions");
		instructionPanel.setBorder(instructionBorder);
		JList instructionList = new JList();
		instructionPanel.setViewportView(instructionList);
		return instructionPanel;
	}
	// creating Registers pane on the UI
	private JScrollPane initRegisterPanel() {
		JScrollPane registerPanel = new JScrollPane(); 
		TitledBorder registerBorder = new TitledBorder("Registers");
		registerPanel.setBorder(registerBorder);
		// creating register arrays having values ? 
		String registers[] = {"R0:", "R1:", "R2:", "R3:", "PC:", "CC:", "IR:", 
								"MAR:", "MBR:", "MSR:", "MFR:", "X1:", "X2:", "X3:"};
		JList registerList = new JList(registers);
		registerPanel.setViewportView(registerList);
		return registerPanel;
	}
}
