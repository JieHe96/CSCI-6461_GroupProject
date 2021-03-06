import java.util.*;

/**
 * Class that defines instruction list and runs instruction.
 */
public class InstructionList {
	
	//Map to store the instruction, using address as key, Instruction as value
	private Map<Integer, Instruction> instructionList;
	//Vector to track the order of the instructions
	private Vector<Integer> addressList;

    public int[] logicInstructionArray;
    public int[] arithmeticInstructionArray;
    public int[] transferInstructionArray;
    public int[] ioInstructionArray;
    public int[] misInstructionArray;
    public int[] fpInstructionArray;
    private int pointer;
    private boolean jump;
	
	/**
	 * Initializes Instructions.
	 */
  	public InstructionList() { // constructor 
		instructionList = new HashMap<Integer, Instruction> ();
		addressList = new Vector<Integer> ();
		logicInstructionArray = new int[] {1,2,3,33,34,18,19,20,21,25,26};
		arithmeticInstructionArray = new int[] {4,5,6,7,16,17};
		transferInstructionArray = new int[] {8,9,10,11,12,13,14,15};
		ioInstructionArray = new int[] {49, 50};
		misInstructionArray = new int[] {0,30};
		fpInstructionArray = new int[] {27,28,29,31,40,41,42};
		pointer = 0;
		jump = false;
		
	} 
  	
  	//initialize trap and machine fault
  	public void initProgram1() {
  		//machine fault routine
  		MainApp.myMemory.writeToMemory(2000, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2001, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2002, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2003, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2004, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2005, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2006, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2007, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2008, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2009, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2010, "0000000000000000");
  		
  		//trap routine 1
  		MainApp.myMemory.writeToMemory(2012, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2013, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2014, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2015, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2016, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2017, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2018, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2019, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2020, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2021, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2022, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2023, "1100100000000000");
  		
  		//trap routine 2
  		MainApp.myMemory.writeToMemory(2025, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2026, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2027, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2028, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2029, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2030, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2031, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2032, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2033, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2034, "1100100000000000");
  		MainApp.myMemory.writeToMemory(2035, "1100010000000000");
  		MainApp.myMemory.writeToMemory(2036, "1100100000000000");
  	}
  	
  	public void initProgram() {
		//Machine code for program1
  		
  		//LDA R0, 31
  		addToInstructionList(62, "0000110000011111");
  		//AIR R0, 1
  		addToInstructionList(63, "0001100000000001");
  		//STR R0 26
  		addToInstructionList(64, "0000100000011010");
  		//LDX R0, X1, 26
  		addToInstructionList(65, "1000010001011010");
  		//LDA R1 20
  		addToInstructionList(66, "0000110100010100");
  		//LDA R2, X1, 1
  		addToInstructionList(67, "0000111001000001");
  		//IN RO, KEY
  		addToInstructionList(10, "1100010000000000");
  		//OUT R0, CON
  		addToInstructionList(69, "1100100000000000");
  		//STR R2, X1, 0
  		addToInstructionList(70, "0000101001000000");
  		//STR R0, X1, 1, 0
  		addToInstructionList(71, "0000100001100000");
  		//AIR R2, 1
  		addToInstructionList(72, "0001101000000001");
  		//SIR R1, 1
  		addToInstructionList(73, "0001110100000001");
  		//JNE
  		addToInstructionList(74, "0010010100001010");

  		
  		//STR R3, X1, 23
  		addToInstructionList(75, "0000101101010111");
  		//LDA R1, X0, 0
  		addToInstructionList(76, "0000110100000000");
  		//STR R1, X1, 24
  		addToInstructionList(77, "0000100101011000");
  		//STR R1, X1, 25
  		addToInstructionList(78, "0000100101011001");
  		//STR R1, X1, 26
  		addToInstructionList(79, "0000100101011010");
  		//STR R1, X1, 27
  		addToInstructionList(80, "0000100101011011");
  		//LDA R1, X1, 0, 0
  		addToInstructionList(81, "0000110101000000");
  		//STR R1, X1, 27
  		addToInstructionList(82, "0000100101011011");
  		//LDA R0, X0, 0, 20
  		addToInstructionList(83, "0000110000010100");
  		//STR R0, X1, 0,28
  		addToInstructionList(84, "0000100001011100");
  		//HLT
  		addToInstructionList(12, "0000000000000000");
  		//IN R0, key
  		addToInstructionList(86, "1100010000000000");
  		//STR R0, X1, 0, 25
  		addToInstructionList(87, "0000100001011001");
  		  
  		
  		
  		//LDR R1, X1, 28
  		addToInstructionList(11, "0000010101011100");
  		//JZ R1, 13
  		addToInstructionList(104,  "0010000100001100");
  	
  		//LDR R2, X1, 28
  		addToInstructionList(105, "0000011001011100");
  		//SIR R2, 1
  		addToInstructionList(89, "0001111000000001");
  		//STR R2, X1, 28
  		addToInstructionList(90, "0000101001011100");
  		//LDR R3, X1, 27
  		addToInstructionList(91, "0000011101011011");
  		//AIR R3, 1
  		addToInstructionList(92, "0001101100000001");
  		//STR R3, X1, 27
  		addToInstructionList(93, "0000101101011011");
  		//LDR R0, X1, 25
  		addToInstructionList(107, "0000010001011001");
  		//SMR R0, X1, 1, 27
  		addToInstructionList(94, "0001010001111011");
  		//STR R0, X1, 26
  		addToInstructionList(95, "0000100001011010");
  		//LDR R2, X1, 23
  		addToInstructionList(96, "0000011001010111");
  		//DVD R0, R2
  		addToInstructionList(97, "0100010010000000");
  		//JNE R0, 11
  		addToInstructionList(98, "0010010000001011");
  		//LDR R2, X1, 26
  		addToInstructionList(99, "0000011001011010");
  		//STR R2, X1, 23
  		addToInstructionList(100, "0000101001010111");
  		//LDR R2, X1, 1, 27
  		addToInstructionList(101, "0000011001111011");
  		//STR R2, X1, 24
  		addToInstructionList(102, "0000101001011000");
  		//OUT 
  		addToInstructionList(103, "1100101000000001");
  		//JNE R1, 11
  		addToInstructionList(106, "0010010100001011");
  		//finish
  		
  	}

	/**
	 * Adds instruction to instruction list.
	 *
	 * @param index address of instruction
	 * @param value value of instruction
	 * @see #addressList
	 */
	public int addToInstructionList(int index, String value) {
		int type = Decode.decodeType(value);
		System.out.println("type " + type);
		switch (type) {
			case 1:
				ArithmeticInstruction newArithIns = new ArithmeticInstruction();
				newArithIns.assignValue(value);
				instructionList.put(index, newArithIns);
				addressList.add(index);
				break;
			case 2:
				TransferInstruction newTransIns = new TransferInstruction();
				newTransIns.assignValue(value);
				instructionList.put(index, newTransIns);
				addressList.add(index);
				break;
			case 3:
				LogicInstruction newLogicIns = new LogicInstruction();
				newLogicIns.assignValue(value);
				instructionList.put(index, newLogicIns);
				addressList.add(index);
				break;
			case 4:
				IOInstruction newIOIns = new IOInstruction();
				newIOIns.assignValue(value);
				instructionList.put(index, newIOIns);
				addressList.add(index);
				break;
			case 5:
				MiscellaneousInstruction newMisIns = new MiscellaneousInstruction();
				newMisIns.assignValue(value);
				instructionList.put(index, newMisIns);
				addressList.add(index);
				break;	
			case 6:
				Instruction errorIns = new Instruction();
				errorIns.assignValue(value);
				instructionList.put(index, errorIns);
				addressList.add(index);
				break;
			case 7:
				Instruction newfpIns = new FPInstruction();
				newfpIns.assignValue(value);
				instructionList.put(index, newfpIns);
				addressList.add(index);
				break;
		}
		int fault = MainApp.myMemory.writeToMemory(index, value);
		return fault;
	}
	
	private void executeHelper(int type, String value) {
		switch (type) { 
			case 1:
				ArithmeticInstruction newArithIns = new ArithmeticInstruction();
				newArithIns.assignValue(value);
				newArithIns.fetchInstruction();
				newArithIns.execute();
				break;
			case 2:
				TransferInstruction newTransIns = new TransferInstruction();
				newTransIns.assignValue(value);
				newTransIns.fetchInstruction();
				newTransIns.execute();
				break;
			case 3:
				LogicInstruction newLogicIns = new LogicInstruction();
				newLogicIns.assignValue(value);
				newLogicIns.fetchInstruction();
				newLogicIns.execute();
				break;
			case 4:
				IOInstruction newIOIns = new IOInstruction();
				newIOIns.assignValue(value);
				newIOIns.fetchInstruction();
				newIOIns.execute();
				break;
			case 5:
				MiscellaneousInstruction newMisIns = new MiscellaneousInstruction();
				newMisIns.assignValue(value);
				newMisIns.fetchInstruction();
				newMisIns.execute();
				break;	
			case 6:
				break;
		}
	}
	
	public void exeMachineFaultIns() {
		for (int i = 2000; i < 2011; i++) {
			if (i == 2000) MainApp.frame.setKeyboard("H");
			else if (i == 2002) MainApp.frame.setKeyboard("A");
			else if (i == 2004) MainApp.frame.setKeyboard("L");
			else if (i == 2006) MainApp.frame.setKeyboard("T");
			else if (i == 2008) MainApp.frame.setKeyboard("/n");
			String value = MainApp.myMemory.readFromMemory(i).convertToString();
			int type = Decode.decodeType(value);
			System.out.println("Type: " + type);
			executeHelper(type, value);
		}
	}
	
	
	public void exeTrapIns1() {
		for (int i = 2012; i < 2024; i++) {
			if (i == 2012) MainApp.frame.setKeyboard("T");
			else if (i == 2014) MainApp.frame.setKeyboard("R");
			else if (i == 2016) MainApp.frame.setKeyboard("A");
			else if (i == 2018) MainApp.frame.setKeyboard("P");
			else if (i == 2020) MainApp.frame.setKeyboard("1");
			else if (i == 2022) MainApp.frame.setKeyboard("/n");
			String value = MainApp.myMemory.readFromMemory(i).convertToString();
			int type = Decode.decodeType(value);
			executeHelper(type, value);	
		}
	}
	
	public void exeTrapIns2() {
		for (int i = 2025; i < 2037; i++) {
			if (i == 2025) MainApp.frame.setKeyboard("T");
			else if (i == 2027) MainApp.frame.setKeyboard("R");
			else if (i == 2029) MainApp.frame.setKeyboard("A");
			else if (i == 2031) MainApp.frame.setKeyboard("P");
			else if (i == 2033) MainApp.frame.setKeyboard("2");
			else if (i == 2035) MainApp.frame.setKeyboard("/n");
			String value = MainApp.myMemory.readFromMemory(i).convertToString();
			int type = Decode.decodeType(value);
			executeHelper(type, value);	
		}
	}

	/**
	 * Runs single instruction.
	 *
	 * @param index address of instruction in instruction list
	 * @see Instruction#fetchInstruction()
	 * @see Instruction#execute()
	 */
	public void runSingleInstruction(int index) {
		if (instructionList.containsKey(index)) {
			pointer = addressList.indexOf(index);
			//check for machine fault
			String temp = instructionList.get(index).getValue().convertToString();
			int type = Decode.decodeType(temp);
			if (type == 6) {
				MachineFault opFault = new MachineFault();
				opFault.handleFault(2);
				System.out.println("Machine Fault: Illegal Operation Code.");
				pointer++;
				return;
			}
			
			instructionList.get(index).fetchInstruction();
			instructionList.get(index).execute();
			
			//remove from addressList
			//addressList.remove(addressList.indexOf(index));
			//remove from instructionList
			//instructionList.remove(index);
			
			pointer++;
			System.out.println("Pointer: " + pointer);
			if (pointer < addressList.size() && !jump) {
				int buff = addressList.indexOf(index);
				MainApp.myRegisters.writeToRegister("PC", String.valueOf(addressList.get(buff+1)), 12);
			}
			else if (jump) {
				jump = false;
			}
			else {
				MainApp.myClock.setFlag(false);
			}
			
		}
	}

	/**
	 * Runs the first Instruction.
	 *
	 * @see Instruction#fetchInstruction()
	 * @see Instruction#execute()
	 */
	public void runFromStart() {
		int index = addressList.get(0);
		instructionList.get(index).fetchInstruction();
		instructionList.get(index).execute();
	}
	
	public int getFirstInsAddr() {
		return addressList.get(0);
	}
	
	public Vector<Integer> getAddrList() {
		return addressList;
	}
	
	public Map<Integer, Instruction> getInsList() {
		return instructionList;
	}
	
	public boolean isLast() {
		return pointer >= addressList.size();
	}
	
	public void setJump(boolean isJump) { jump = isJump; };
}
