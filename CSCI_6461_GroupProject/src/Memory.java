import java.util.*;

/**
 * Class that defines memory and write and read memory contents
 */
public class Memory {
	
	//private variable need for the memory class
	private Vector<Word> memoVec;
	/** Initializes memory */
	public Memory() {
		initMemory();
	}

	/**
	 * Creates vector for memory
	 *
	 * @return
	 */
	public Vector<Word> GetMemo() {return memoVec;} // Creating vector for memory vectors
	
	
	private void initMemory() {
		memoVec = new Vector<Word> (2048);
		for (int i = 0; i < 2048; i++) 
		{
			Word ept = new Word();
			if (i == 1) {
				String value = "0000000000000110";
		    	for (int j = 0; j < 16; j++) {
		    		if (value.charAt(j) == '1') ept.set(j, true);
		    		else ept.set(j, false);
		    	}
			}
			memoVec.add(ept);
		}
	}

	/**
	 * Writes value to memory
	 *
	 * @param index the address of memory
	 * @param value the value of memory
	 */
	public void writeToMemory(int index, String value) {
		if (index >= 0 && index <= 5) {
			MachineFault mrlFault = new MachineFault();
			mrlFault.handleFault(0);
			System.out.println("Machine Fault: Illegal Memory Address to Reserved Locations.");
			return;
		}
		if (index >= 2048) {
			MachineFault mrlFault = new MachineFault();
			mrlFault.handleFault(3);
			System.out.println("Machine Fault: Illegal Memory Address beyond 2048.");
			return;
		}
		Word word = new Word();
    	for (int i = 0; i < 16; i++) {
    		if (value.charAt(i) == '1') word.set(i, true);
    		else word.set(i, false);
    	}
		memoVec.set(index, word);
	}

	/**
	 * Reads value from memory by index
	 *
	 * @param index the address of memory
	 * @return the value of memory
	 */
	public Word readFromMemory(int index) {
		return memoVec.get(index);
	}
}
