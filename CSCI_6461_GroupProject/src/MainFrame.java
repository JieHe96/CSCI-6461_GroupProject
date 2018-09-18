import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;


/** The Mainframe class extends the JFrame class to create the layout of the UI and the various panes 
 * present in the UI */


public class MainFrame extends JFrame{
	
	JTextField inputText;
	JList<String> instructionList;
	JList<String> registerList;
	DefaultListModel<String> instructionModel;
	DefaultListModel<String> registerModel;
	
	
	public MainFrame(String title) {
		super(title);
		
		init();
		setLayout(new GridLayout(2,1));
		JPanel top = new JPanel(new GridLayout(1,2));
		
		//Instruction Section
		JScrollPane instructionPanel = initInstructionPanel();
		top.add(instructionPanel);
		//Register Section
		JScrollPane registerPanel = initRegisterPanel();
		top.add(registerPanel);
		
		JPanel bottomPanel = initUIPanel();
		add(top);
		add(bottomPanel);
	}
	
	// creating instruction pane on the UI
	private JScrollPane initInstructionPanel() {
		JScrollPane instructionPanel = new JScrollPane();
		TitledBorder instructionBorder = new TitledBorder("Instructions");
		instructionPanel.setBorder(instructionBorder);
		instructionModel = new DefaultListModel<String>();
		instructionList = new JList<String>();
		instructionList.setModel(instructionModel);
		instructionPanel.setViewportView(instructionList);
		return instructionPanel;
	}
	// creating Registers pane on the UI
	private JScrollPane initRegisterPanel() {
		JScrollPane registerPanel = new JScrollPane(); 
		TitledBorder registerBorder = new TitledBorder("Registers");
		registerPanel.setBorder(registerBorder);
		// creating register arrays having values ? 
		registerModel = new DefaultListModel<String>();
		registerModel.addElement("R0:");
		registerModel.addElement("R1:");
		registerModel.addElement("R2:");
		registerModel.addElement("R3:");
		registerModel.addElement("R4:");
		registerModel.addElement("PC:");
		registerModel.addElement("CC:");
		registerModel.addElement("IR:");
		registerModel.addElement("MAR:");
		registerModel.addElement("MBR:");
		registerModel.addElement("MSR:");
		registerModel.addElement("MFR:");
		registerModel.addElement("X1:");
		registerModel.addElement("X2:");
		registerModel.addElement("X3:");
		registerList = new JList<String>();
		registerList.setModel(registerModel);
		registerPanel.setViewportView(registerList);
		return registerPanel;
	}
	
	private JPanel initUIPanel () {
		JPanel bottom = new JPanel(new GridLayout(2,1));
		
		//Input Section
		JPanel inputPanel = new JPanel(new BorderLayout());
		TitledBorder inputBorder = new TitledBorder("Input");
		inputPanel.setBorder(inputBorder);
		JLabel inputLabel = new JLabel("Instruction");
		inputText = new JTextField();
		JButton inputButton = new JButton("Add Instruction");
		inputButton.addActionListener(inputButtonListener);
		inputPanel.add(inputLabel, BorderLayout.WEST);
		inputPanel.add(inputText, BorderLayout.CENTER);
		inputPanel.add(inputButton, BorderLayout.EAST);
		bottom.add(inputPanel);
				
		//Control Section
		JPanel controlPanel = new JPanel(new FlowLayout());
		TitledBorder controlBorder = new TitledBorder("Control");
		controlPanel.setBorder(controlBorder);
		JButton singleRunButton = new JButton("Single Run");
		singleRunButton.addActionListener(singleRunButtonListener);
		JButton loadButton = new JButton("Load");
		JButton startButton = new JButton("Start");
		JButton stopButton = new JButton("Stop");
		controlPanel.add(singleRunButton);
		controlPanel.add(loadButton);
		controlPanel.add(startButton);
		controlPanel.add(stopButton);
		bottom.add(controlPanel);
		return bottom;
		
	}
	
	private ActionListener inputButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String ins = inputText.getText();
			instructionModel.addElement(ins);
			instructionList.setModel(instructionModel);
			MainApp.myInstructions.AddToPC(ins);
		}
	};
	
	private ActionListener singleRunButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String ins = instructionList.getModel().getElementAt(0);
			registerModel.setElementAt("IR:" + ins, 7);
			registerList.setModel(registerModel);
			System.out.println(ins);
			MainApp.myInstructions.FetchInstruction(ins);
			MainApp.myInstructions.IncreasePC();
			registerModel.setElementAt("PC: " + MainApp.myInstructions.GetCounter(), 5);
			registerList.setModel(registerModel);
			
			//delete one instruction
			instructionModel.removeElementAt(0);
			instructionList.setModel(instructionModel);
			//update the PC
			if (MainApp.myInstructions.ResetPC()) {
				registerModel.setElementAt("PC: " + MainApp.myInstructions.GetCounter(), 5);
				registerList.setModel(registerModel);
			}
			
		}
	};
	
	private void init() {
		
	}
	
}
