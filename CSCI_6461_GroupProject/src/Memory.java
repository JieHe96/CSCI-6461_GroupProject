import java.util.*;

public class Memory {
	
	//private variable need for the memory class
	private Vector<Word> memoVec;
	
	public Memory() {
		initMemory();
	}

	public Vector<Word> GetMemo() {return memoVec;} // Creating vector for memory vectors
	
	
	private void initMemory() {
		memoVec = new Vector<Word> (2048);
		for (int i = 0; i < 2048; i++) 
		{
			Word ept = new Word();
			memoVec.add(ept);
		}
	}
	
	public void writeToMemory(int index, String value) {
		Word word = new Word();
    	for (int i = 0; i < 16; i++) {
    		if (value.charAt(i) == '1') word.set(i, true);
    		else word.set(i, false);
    	}
		memoVec.set(index, word);
	}
	
	public Word readFromMemory(int index) {
		return memoVec.get(index);
	}
}
