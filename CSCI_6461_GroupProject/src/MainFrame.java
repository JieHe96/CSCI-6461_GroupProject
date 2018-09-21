import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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
	
	JTextField addrText;
	JTextField valueText;
	JTextField pcText;
	JButton singleRunButton;
	JButton iplButton;
	JTextField irText;
	JTextField marText;
	JTextField mbrText;
	JTextField r0Text;
	JTextField r1Text;
	JTextField r2Text;
	JTextField r3Text;
	JTextField x1Text;
	JTextField x2Text;
	JTextField x3Text;
	DefaultListModel<String> instructionModel;
	JList<String> instructionList;
	
	public MainFrame(String title) {
		super(title);
		
		setLayout(new BorderLayout());
		JPanel top = new JPanel(new GridLayout(1,2));
		
		//Instruction Section
		JScrollPane instructionPanel = initInstructionPanel();
		top.add(instructionPanel);
		//Register Section
		JPanel registerPanel = initRPanel();
		top.add(registerPanel);
		
		JPanel bottomPanel = initUIPanel();
		add(top, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.PAGE_END);
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

	
	private JPanel initRPanel() {
		JPanel registerPanel = new JPanel(new GridLayout(14, 1));
		
		JPanel r0Panel = new JPanel(new BorderLayout());
		JLabel r0Label = new JLabel("R0");
		JButton r0Button = new JButton("Write");
		r0Text = new JTextField();
		r0Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainApp.myRegisters.writeToRegister("R0", r0Text.getText(), 16);
				System.out.println(MainApp.myRegisters.getRegister("R0", false));
			}
		});
		r0Panel.add(r0Label, BorderLayout.WEST);
		r0Panel.add(r0Text, BorderLayout.CENTER);
		r0Panel.add(r0Button, BorderLayout.EAST);

		JPanel r1Panel = new JPanel(new BorderLayout());
		JLabel r1Label = new JLabel("R1");
		JButton r1Button = new JButton("Write");
		r1Text = new JTextField();
		r1Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainApp.myRegisters.writeToRegister("R1", r1Text.getText(), 16);
				System.out.println(MainApp.myRegisters.getRegister("R1", false));
			}
		});
		r1Panel.add(r1Label, BorderLayout.WEST);
		r1Panel.add(r1Text, BorderLayout.CENTER);
		r1Panel.add(r1Button, BorderLayout.EAST);
		
		JPanel r2Panel = new JPanel(new BorderLayout());
		JLabel r2Label = new JLabel("R2");
		JButton r2Button = new JButton("Write");
		r2Text = new JTextField();
		r2Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainApp.myRegisters.writeToRegister("R2", r2Text.getText(), 16);
				System.out.println(MainApp.myRegisters.getRegister("R2", false));
			}
		});
		r2Panel.add(r2Label, BorderLayout.WEST);
		r2Panel.add(r2Text, BorderLayout.CENTER);
		r2Panel.add(r2Button, BorderLayout.EAST);
		
		JPanel r3Panel = new JPanel(new BorderLayout());
		JLabel r3Label = new JLabel("R3");
		JButton r3Button = new JButton("Write");
		r3Text = new JTextField();
		r3Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainApp.myRegisters.writeToRegister("R3", r3Text.getText(), 16);
				System.out.println(MainApp.myRegisters.getRegister("R3", false));
			}
		});
		r3Panel.add(r3Label, BorderLayout.WEST);
		r3Panel.add(r3Text, BorderLayout.CENTER);
		r3Panel.add(r3Button, BorderLayout.EAST);
		
		JPanel x1Panel = new JPanel(new BorderLayout());
		JLabel x1Label = new JLabel("X1");
		JButton x1Button = new JButton("Write");
		x1Text = new JTextField();
		x1Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainApp.myRegisters.writeToRegister("X1", x1Text.getText(), 16);
				System.out.println(MainApp.myRegisters.getRegister("X1", false));
			}
		});
		x1Panel.add(x1Label, BorderLayout.WEST);
		x1Panel.add(x1Text, BorderLayout.CENTER);
		x1Panel.add(x1Button, BorderLayout.EAST);
		
		JPanel x2Panel = new JPanel(new BorderLayout());
		JLabel x2Label = new JLabel("X2");
		JButton x2Button = new JButton("Write");
		x2Text = new JTextField();
		x2Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainApp.myRegisters.writeToRegister("X2", x2Text.getText(), 16);
				System.out.println(MainApp.myRegisters.getRegister("X2", false));
			}
		});
		x2Panel.add(x2Label, BorderLayout.WEST);
		x2Panel.add(x2Text, BorderLayout.CENTER);
		x2Panel.add(x2Button, BorderLayout.EAST);
		
		JPanel x3Panel = new JPanel(new BorderLayout());
		JLabel x3Label = new JLabel("X3");
		JButton x3Button = new JButton("Write");
		x3Text = new JTextField();
		x3Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainApp.myRegisters.writeToRegister("X3", x3Text.getText(), 16);
				System.out.println(MainApp.myRegisters.getRegister("X3", false));
			}
		});
		x3Panel.add(x3Label, BorderLayout.WEST);
		x3Panel.add(x3Text, BorderLayout.CENTER);
		x3Panel.add(x3Button, BorderLayout.EAST);
		
		JPanel pcPanel = new JPanel(new BorderLayout());
		JLabel pcLabel = new JLabel("PC");
		JButton pcButton = new JButton("Write");
		pcText = new JTextField();
		pcButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainApp.myRegisters.writeToRegister("PC", pcText.getText(), 12);
				System.out.println(MainApp.myRegisters.getRegister("PC", true));
			}
		});
		pcPanel.add(pcLabel, BorderLayout.WEST);
		pcPanel.add(pcText, BorderLayout.CENTER);
		pcPanel.add(pcButton, BorderLayout.EAST);
		
		JPanel marPanel = new JPanel(new BorderLayout());
		JLabel marLabel = new JLabel("MAR");
		JButton marButton = new JButton("Write");
		marText = new JTextField();
		marPanel.add(marLabel, BorderLayout.WEST);
		marPanel.add(marText, BorderLayout.CENTER);
		marPanel.add(marButton, BorderLayout.EAST);
		
		JPanel irPanel = new JPanel(new BorderLayout());
		JLabel irLabel = new JLabel("IR");
		irText = new JTextField();
		irPanel.add(irLabel, BorderLayout.WEST);
		irPanel.add(irText, BorderLayout.CENTER);
		
		JPanel mbrPanel = new JPanel(new BorderLayout());
		JLabel mbrLabel = new JLabel("MBR");
		JButton mbrButton = new JButton("Write");
		mbrText = new JTextField();
		mbrPanel.add(mbrLabel, BorderLayout.WEST);
		mbrPanel.add(mbrText, BorderLayout.CENTER);
		mbrPanel.add(mbrButton, BorderLayout.EAST);
		
		JPanel ccPanel = new JPanel(new BorderLayout());
		JLabel ccLabel = new JLabel("CC");
		JTextField ccText = new JTextField();
		ccPanel.add(ccLabel, BorderLayout.WEST);
		ccPanel.add(ccText, BorderLayout.CENTER);
		
		JPanel mfrPanel = new JPanel(new BorderLayout());
		JLabel mfrLabel = new JLabel("MFR");
		JTextField mfrText = new JTextField();
		mfrPanel.add(mfrLabel, BorderLayout.WEST);
		mfrPanel.add(mfrText, BorderLayout.CENTER);
		
		JPanel msrPanel = new JPanel(new BorderLayout());
		JLabel msrLabel = new JLabel("MSR");
		JTextField msrText = new JTextField();
		msrPanel.add(msrLabel, BorderLayout.WEST);
		msrPanel.add(msrText, BorderLayout.CENTER);
		
		registerPanel.add(r0Panel);
		registerPanel.add(r1Panel);
		registerPanel.add(r2Panel);
		registerPanel.add(r3Panel);
		registerPanel.add(x1Panel);
		registerPanel.add(x2Panel);
		registerPanel.add(x3Panel);
		registerPanel.add(pcPanel);
		registerPanel.add(marPanel);
		registerPanel.add(irPanel);
		registerPanel.add(mbrPanel);
		registerPanel.add(ccPanel);
		registerPanel.add(mfrPanel);
		registerPanel.add(msrPanel);
		
		return registerPanel;
	}
	
	private JPanel initUIPanel () {
		JPanel bottom = new JPanel(new GridLayout(2,1));
		
		//Input Section
		JPanel inputPanel = new JPanel(new GridLayout(1,3));
		TitledBorder inputBorder = new TitledBorder("Input");
		inputPanel.setBorder(inputBorder);
		
		JPanel valuePanel = new JPanel(new BorderLayout());
		JLabel valueLabel = new JLabel("Value: ");
		valueText = new JTextField();
		valuePanel.add(valueLabel, BorderLayout.WEST);
		valuePanel.add(valueText, BorderLayout.CENTER);
		
		JPanel addrPanel = new JPanel(new BorderLayout());
		JLabel addrLabel = new JLabel("Address: ");
		addrText = new JTextField();
		addrPanel.add(addrLabel, BorderLayout.WEST);
		addrPanel.add(addrText, BorderLayout.CENTER);
		
		JPanel rwPanel = new JPanel(new GridLayout(1,2));
		JButton readButton = new JButton("Read");
		JButton writeButton = new JButton("Write");
		writeButton.addActionListener(writeButtonListener);
		rwPanel.add(readButton);
		rwPanel.add(writeButton);
		
		inputPanel.add(valuePanel, BorderLayout.WEST);
		inputPanel.add(addrPanel, BorderLayout.CENTER);
		inputPanel.add(rwPanel, BorderLayout.EAST);
		bottom.add(inputPanel);
				
		//Control Section
		JPanel controlPanel = new JPanel(new FlowLayout());
		TitledBorder controlBorder = new TitledBorder("Control");
		controlPanel.setBorder(controlBorder);
		iplButton = new JButton("IPL");
		iplButton.addActionListener(iplButtonListener);
		singleRunButton = new JButton("Single Run");
		singleRunButton.addActionListener(singleRunButtonListener);
		JButton loadButton = new JButton("Load");
		JButton startButton = new JButton("Start");
		JButton stopButton = new JButton("Stop");
		controlPanel.add(iplButton);
		controlPanel.add(singleRunButton);
		controlPanel.add(loadButton);
		controlPanel.add(startButton);
		controlPanel.add(stopButton);
		bottom.add(controlPanel);
		return bottom;
		
	}
	
	private ActionListener writeButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String addr = addrText.getText();
			String value = valueText.getText();
			int index = Integer.parseInt(addr);
			System.out.println(index);
			MainApp.myMemory.writeToMemory(index, value);
			MainApp.myInstructionList.addToInstructionList(index, value);
			//SetPC(addr);
		}
	};
	
	private ActionListener singleRunButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String pcNum = pcText.getText();
			int index = Decode.ToDecimal(pcNum);
			MainApp.myInstructionList.runSingleInstruction(index);
			String ir = MainApp.myRegisters.getRegister("IR", false);
			String mar = MainApp.myRegisters.getRegister("MAR", true);
			String mbr = MainApp.myRegisters.getRegister("MBR", false);
			String r0 = MainApp.myRegisters.getRegister("R0", false);
			String r1 = MainApp.myRegisters.getRegister("R1", false);
			String r2 = MainApp.myRegisters.getRegister("R2", false);
			String r3 = MainApp.myRegisters.getRegister("R3", false);
			String x1 = MainApp.myRegisters.getRegister("X1", true);
			String x2 = MainApp.myRegisters.getRegister("X2", true);
			String x3 = MainApp.myRegisters.getRegister("X3", true);
			String pc = MainApp.myRegisters.getRegister("PC", true);
			irText.setText(ir);
			marText.setText(mar);
			mbrText.setText(mbr);
			r0Text.setText(r0);
			r1Text.setText(r1);
			r2Text.setText(r2);
			r3Text.setText(r3);
			x1Text.setText(x1);
			x2Text.setText(x2);
			x3Text.setText(x3);
			pcText.setText(pc);
			System.out.println(MainApp.myMemory.GetMemo().get(8).convertToString());
		}
	};
	
	private ActionListener iplButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int index = MainApp.myInstructionList.getFirstInsAddr();
			SetPC(String.valueOf(index));
			Vector<Integer> addrList = MainApp.myInstructionList.getAddrList();
			for (int i : addrList) {
				Word val = MainApp.myInstructionList.getInsList().get(i).getValue();
				String insValue = val.convertToString();
				String ele = "Address: " + String.valueOf(i) + "     " + "Value: " + insValue;
				instructionModel.addElement(ele);
			}
			instructionList.setModel(instructionModel);
		}
	};
	
	private void SetPC(String value) {
		//String pcStr = pcText.getText();
		//if (pcText.getText().isEmpty()) {
			pcText.setText(value);
		//}
	}
	
}
