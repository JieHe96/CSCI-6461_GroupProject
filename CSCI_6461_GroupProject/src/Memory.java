import java.util.*;

public class Memory {
	
	//private variable need for the memory class
	private Vector<Word> memoVec;
	private Register registers;
	
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
}
