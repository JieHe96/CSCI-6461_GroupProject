import java.awt.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

public class UIPanel extends JPanel{
	public UIPanel(GridLayout gridLayout) {
		super(gridLayout);
		
		//Input Section
		JPanel inputPanel = new JPanel(new BorderLayout());
		TitledBorder inputBorder = new TitledBorder("Input");
		inputPanel.setBorder(inputBorder);
		JLabel inputLabel = new JLabel("Instruction");
		JTextField inputText = new JTextField();
		JButton inputButton = new JButton("Add Instruction");
		inputPanel.add(inputLabel, BorderLayout.WEST);
		inputPanel.add(inputText, BorderLayout.CENTER);
		inputPanel.add(inputButton, BorderLayout.EAST);
		add(inputPanel);
				
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
		add(controlPanel);
		
	}
}
