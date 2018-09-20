import java.util.*;

public class InstructionList {
	
	private Map<Integer, Instruction> instructionList;
	
	public InstructionList() {
		instructionList = new HashMap<Integer, Instruction> ();
	}
	
	public void addToInstructionList(int index, String value) {
		Instruction newIns = new Instruction();
		newIns.assignValue(value);
		instructionList.put(index, newIns);
	}
	
	public void runSingleInstruction(int index) {
		if (instructionList.containsKey(index)) {
			instructionList.get(index).fetchInstruction();
		}
	}
}
