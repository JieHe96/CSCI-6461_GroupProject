import java.util.*;

public class Memory {
	
	//private variable need for the memory class
	private Vector<Integer> addrVec;
	private Vector<Integer> memoVec;
	private Register registers;
	
	public Memory() {
		addrVec = new Vector<Integer> (32);
		memoVec = new Vector<Integer> (2048);
		registers = new Register();
		initRegister();
	}
	
	public Vector<Integer> GetAddr() {return addrVec;} // Creating vector for address vectors

	public Vector<Integer> GetMemo() {return memoVec;} // Creating vector for memory vectors
	
	public Register GetRegister() { return registers; };
	
	private void initRegister() {
		for (int i = 0; i < 32; i++) 
		{
			addrVec.add(0);
		}
		addrVec.set(0, 14);
	}
}
