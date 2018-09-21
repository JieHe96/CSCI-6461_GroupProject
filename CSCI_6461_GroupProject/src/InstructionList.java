import java.util.*;

public class InstructionList {
	
	//Map to store the instruction, using address as key, Instruction as value
	private Map<Integer, Instruction> instructionList;
	//Vector to track the order of the instructions
	private Vector<Integer> addressList;
	
	public InstructionList() {
		instructionList = new HashMap<Integer, Instruction> ();
		addressList = new Vector<Integer> ();
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
