import java.util.*;

public class InstructionList {
	
	//Map to store the instruction, using address as key, Instruction as value
	private Map<Integer, Instruction> instructionList;
	//Vector to track the order of the instructions
	private Vector<Integer> addressList;
	
	public InstructionList() { // constructor 
		instructionList = new HashMap<Integer, Instruction> ();
		addressList = new Vector<Integer> ();
		//Initialising Instructions for IPL
		//LDA r with some address 
		addToInstructionList(6,"0000110000000001");  
		addToInstructionList(7,"0000110100000010");
		addToInstructionList(8,"0000111000000100");
		addToInstructionList(9,"0000111100000100");
		
	}
	
	public void addToInstructionList(int index, String value) {
		Instruction newIns = new Instruction();
		newIns.assignValue(value);
		instructionList.put(index, newIns);
		addressList.add(index);
	}
	
	public void runSingleInstruction(int index) {
		if (instructionList.containsKey(index)) {
			instructionList.get(index).fetchInstruction();
			instructionList.get(index).execute();
			//remove from addressList
			addressList.remove(addressList.indexOf(index));
			//remove from instructionList
			instructionList.remove(index);
			
		}
	}
	
	public void runFromStart() {
		int index = addressList.get(0);
		instructionList.get(index).fetchInstruction();
		instructionList.get(index).execute();
	}
	
	public int getFirstInsAddr() {
		return addressList.get(0);
	}
}
