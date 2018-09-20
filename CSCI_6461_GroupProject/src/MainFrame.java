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
	
	JTextField addrText;
	JTextField valueText;
	JTextField pcText;
	
	public MainFrame(String title) {
		super(title);
		
		init();
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
		DefaultListModel instructionModel = new DefaultListModel<String>();
		JList instructionList = new JList<String>();
		instructionList.setModel(instructionModel);
		instructionPanel.setViewportView(instructionList);
		return instructionPanel;
	}

	
	private JPanel initRPanel() {
		JPanel registerPanel = new JPanel(new GridLayout(14, 1));
		
		JPanel r0Panel = new JPanel(new BorderLayout());
		JLabel r0Label = new JLabel("R0");
		JButton r0Button = new JButton("Write");
		JTextField r0Text = new JTextField();
		r0Panel.add(r0Label, BorderLayout.WEST);
		r0Panel.add(r0Text, BorderLayout.CENTER);
		r0Panel.add(r0Button, BorderLayout.EAST);

		JPanel r1Panel = new JPanel(new BorderLayout());
		JLabel r1Label = new JLabel("R1");
		JButton r1Button = new JButton("Write");
		JTextField r1Text = new JTextField();
		r1Panel.add(r1Label, BorderLayout.WEST);
		r1Panel.add(r1Text, BorderLayout.CENTER);
		r1Panel.add(r1Button, BorderLayout.EAST);
		
		JPanel r2Panel = new JPanel(new BorderLayout());
		JLabel r2Label = new JLabel("R2");
		JButton r2Button = new JButton("Write");
		JTextField r2Text = new JTextField();
		r2Panel.add(r2Label, BorderLayout.WEST);
		r2Panel.add(r2Text, BorderLayout.CENTER);
		r2Panel.add(r2Button, BorderLayout.EAST);
		
		JPanel r3Panel = new JPanel(new BorderLayout());
		JLabel r3Label = new JLabel("R3");
		JButton r3Button = new JButton("Write");
		JTextField r3Text = new JTextField();
		r3Panel.add(r3Label, BorderLayout.WEST);
		r3Panel.add(r3Text, BorderLayout.CENTER);
		r3Panel.add(r3Button, BorderLayout.EAST);
		
		JPanel x1Panel = new JPanel(new BorderLayout());
		JLabel x1Label = new JLabel("X1");
		JButton x1Button = new JButton("Write");
		JTextField x1Text = new JTextField();
		x1Panel.add(x1Label, BorderLayout.WEST);
		x1Panel.add(x1Text, BorderLayout.CENTER);
		x1Panel.add(x1Button, BorderLayout.EAST);
		
		JPanel x2Panel = new JPanel(new BorderLayout());
		JLabel x2Label = new JLabel("X2");
		JButton x2Button = new JButton("Write");
		JTextField x2Text = new JTextField();
		x2Panel.add(x2Label, BorderLayout.WEST);
		x2Panel.add(x2Text, BorderLayout.CENTER);
		x2Panel.add(x2Button, BorderLayout.EAST);
		
		JPanel x3Panel = new JPanel(new BorderLayout());
		JLabel x3Label = new JLabel("X3");
		JButton x3Button = new JButton("Write");
		JTextField x3Text = new JTextField();
		x3Panel.add(x3Label, BorderLayout.WEST);
		x3Panel.add(x3Text, BorderLayout.CENTER);
		x3Panel.add(x3Button, BorderLayout.EAST);
		
		JPanel pcPanel = new JPanel(new BorderLayout());
		JLabel pcLabel = new JLabel("PC");
		JButton pcButton = new JButton("Write");
		pcText = new JTextField();
		pcPanel.add(pcLabel, BorderLayout.WEST);
		pcPanel.add(pcText, BorderLayout.CENTER);
		pcPanel.add(pcButton, BorderLayout.EAST);
		
		JPanel marPanel = new JPanel(new BorderLayout());
		JLabel marLabel = new JLabel("MAR");
		JButton marButton = new JButton("Write");
		JTextField marText = new JTextField();
		marPanel.add(marLabel, BorderLayout.WEST);
		marPanel.add(marText, BorderLayout.CENTER);
		marPanel.add(marButton, BorderLayout.EAST);
		
		JPanel irPanel = new JPanel(new BorderLayout());
		JLabel irLabel = new JLabel("IR");
		JTextField irText = new JTextField();
		irPanel.add(irLabel, BorderLayout.WEST);
		irPanel.add(irText, BorderLayout.CENTER);
		
		JPanel mbrPanel = new JPanel(new BorderLayout());
		JLabel mbrLabel = new JLabel("MBR");
		JButton mbrButton = new JButton("Write");
		JTextField mbrText = new JTextField();
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
		mbrPanel.add(msrLabel, BorderLayout.WEST);
		mbrPanel.add(msrText, BorderLayout.CENTER);
		
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
		JButton singleRunButton = new JButton("Single Run");
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
	
	private ActionListener writeButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String addr = addrText.getText();
			String value = valueText.getText();
			int index = Integer.parseInt(addr);
			System.out.println(index);
			MainApp.myMemory.writeToMemory(index, value);
			SetPC(addr);
		}
	};
	
	private void SetPC(String value) {
		String pcStr = pcText.getText();
		if (pcText.getText().isEmpty()) {
			pcText.setText(value);
		}
	}
	
	private void init() {
		
	}
	
}
