import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

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
	
	private JScrollPane initInstructionPanel() {
		JScrollPane instructionPanel = new JScrollPane();
		TitledBorder instructionBorder = new TitledBorder("Instructions");
		instructionPanel.setBorder(instructionBorder);
		JList instructionList = new JList();
		instructionPanel.setViewportView(instructionList);
		return instructionPanel;
	}
	
	private JScrollPane initRegisterPanel() {
		JScrollPane registerPanel = new JScrollPane(); 
		TitledBorder registerBorder = new TitledBorder("Registers");
		registerPanel.setBorder(registerBorder);
		String registers[] = {"R0:", "R1:", "R2:", "R3:", "PC:", "CC:", "IR:", 
								"MAR:", "MBR:", "MSR:", "MFR:", "X1:", "X2:", "X3:"};
		JList registerList = new JList(registers);
		registerPanel.setViewportView(registerList);
		return registerPanel;
	}
}
