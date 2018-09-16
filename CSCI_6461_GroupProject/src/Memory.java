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
	}
	
	public Vector<Integer> GetAddr() {return addrVec;}
	public Vector<Integer> GetMemo() {return memoVec;}
	public Register GetRegister() { return registers; };
}
