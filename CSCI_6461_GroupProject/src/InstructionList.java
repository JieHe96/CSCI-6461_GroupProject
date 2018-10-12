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
	
	/**
	 * Initializes Instructions.
	 */
  	public InstructionList() { // constructor 
		instructionList = new HashMap<Integer, Instruction> ();
		addressList = new Vector<Integer> ();
		logicInstructionArray = new int[] {1, 2, 3, 33, 34,18,19,20,21,25,26};
		arithmeticInstructionArray = new int[] {4,5,6,7,16,17};
		transferInstructionArray = new int[] {8,9,10,11,12,13,14,15};
		ioInstructionArray = new int[] {49, 50};
		//initProgram();
		
	}
  	
  	public void initProgram() {
		//Initializing Instructions for IPL
		//LDA r with some address 
//		addToInstructionList(6,"0000110000011001");  
//		addToInstructionList(7,"0000100000010100");
//		addToInstructionList(8,"0000110000000011");
//		addToInstructionList(9,"0000100000011110");
//		addToInstructionList(10,"1000010001011110");
//		addToInstructionList(11,"0000111100011101");
//		addToInstructionList(12,"0000101101010110");
//		addToInstructionList(13,"0000011000110100");
//		addToInstructionList(14,"0000101001110001");
//		addToInstructionList(15,"1000100001110100");
  		addToInstructionList(100, "1100010000000000");
  		addToInstructionList(101, "0000100000001010");
  		addToInstructionList(102, "1100100000000000");
  		
  		addToInstructionList(103, "1100010000000000");
  		addToInstructionList(104, "0000100000001011");
  		addToInstructionList(105, "1100100000000000");
  		
  		addToInstructionList(106, "1100010000000000");
  		addToInstructionList(107, "0000100000001100");
  		addToInstructionList(108, "1100100000000000");
  		
  		addToInstructionList(109, "1100010000000000");
  		addToInstructionList(110, "0000100000001101");
  		addToInstructionList(111, "1100100000000000");
  	}

	/**
	 * Adds instruction to instruction list.
	 *
	 * @param index address of instruction
	 * @param value value of instruction
	 * @see #addressList
	 */
	public void addToInstructionList(int index, String value) {
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
		}
		//Instruction newIns = new Instruction();

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
			instructionList.get(index).fetchInstruction();
			instructionList.get(index).execute();
			//remove from addressList
			addressList.remove(addressList.indexOf(index));
			//remove from instructionList
			instructionList.remove(index);
			if (!addressList.isEmpty())
				MainApp.myRegisters.writeToRegister("PC", String.valueOf(addressList.get(0)), 12);
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
}
