import java.util.BitSet;
import java.util.stream.IntStream;

/**Class that defines instruction objects and executes instruction. */

public class Instruction {
	
	protected Word value;
	
	public Instruction () {
		value = new Word();
	}
	
	public Word getValue() {
    	return value;
    }


	public void assignValue(String val) {
    	for (int i = 0; i < 16; i++) {
    		if (val.charAt(i) == '1') value.set(i, true);
    		else value.set(i, false);
    	}
	}
	
	public void fetchInstruction() {
	}
	
	public void execute() {
	}
}