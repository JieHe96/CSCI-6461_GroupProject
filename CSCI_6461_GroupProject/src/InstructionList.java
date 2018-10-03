import java.util.*;

/**
 * Class that defines instruction list and runs instruction.
 */
public class InstructionList {
	
	//Map to store the instruction, using address as key, Instruction as value
	private Map<Integer, Instruction> instructionList;
	//Vector to track the order of the instructions
	private Vector<Integer> addressList;

	/**
	 * Initializes Instructions.
	 */
  	public InstructionList() { // constructor 
		instructionList = new HashMap<Integer, Instruction> ();
		addressList = new Vector<Integer> ();
		//initProgram();
	}
  	
  	public void initProgram() {
		//Initializing Instructions for IPL
		//LDA r with some address 
		addToInstructionList(6,"0000110000011001");  
		addToInstructionList(7,"0000100000010100");
		addToInstructionList(8,"0000110000000011");
		addToInstructionList(9,"0000100000011110");
		addToInstructionList(10,"1000010001011110");  
		addToInstructionList(11,"0000111100011101");
		addToInstructionList(12,"0000101101010110");
		addToInstructionList(13,"0000011000110100");
		addToInstructionList(14,"0000101001110001");
		addToInstructionList(15,"1000100001110100");
  	}

	/**
	 * Adds instruction to instruction list.
	 *
	 * @param index address of instruction
	 * @param value value of instruction
	 * @see #addressList
	 */
	public void addToInstructionList(int index, String value) {
		Instruction newIns = new Instruction();
		newIns.assignValue(value);
		instructionList.put(index, newIns);
		addressList.add(index);
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
		//instructionList.get(index).execute();
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
