import java.util.*;

public class Memory {
	
	//private variable need for the memory class
	private Vector<Integer> addrVec;
	private Vector<Word> memoVec;
	private Register registers;
	
	public Memory() {
		addrVec = new Vector<Integer> (32);
		memoVec = new Vector<Word> (2048);
		registers = new Register();
		initRegister();
		initMemory();
	}
	
	public Vector<Integer> GetAddr() {return addrVec;} // Creating vector for address vectors

	public Vector<Word> GetMemo() {return memoVec;} // Creating vector for memory vectors
	
	public Register GetRegister() { return registers; };
	
	private void initRegister() {
		for (int i = 0; i < 32; i++) 
		{
			addrVec.add(0);
		}
		addrVec.set(0, 14);
	}
	
	private void initMemory() {
		for (int i = 0; i < 2048; i++) 
		{
			Word ept = new Word();
			memoVec.add(ept);
		}
	}
	
	public Word GetGRValue(int regNum) {
		switch (regNum) {
			case 0:
				return memoVec.get(1);
			case 1:
				return memoVec.get(2);
			case 2:
				return memoVec.get(3);
			case 3:
				return memoVec.get(4);
		}
		return null;
	}
	
	public Word GetIXValue(int regNum) {
		switch (regNum) {
			case 0:
				return null;
			case 1:
				return memoVec.get(11);
			case 2:
				return memoVec.get(12);
			case 3:
				return memoVec.get(13);
		}
		return null;
	}
}
