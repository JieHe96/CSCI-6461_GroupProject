import java.util.*;

public class Register {
	/**
	 * {"R0:", "R1:", "R2:", "R3:", "PC:", "CC:", "IR:", 
	 * "MAR:", "MBR:", "MSR:", "MFR:", "X1:", "X2:", "X3:"};
	 */
	public BitSet r0;
	public BitSet r1;
	public BitSet r2;
	public BitSet r3;
	public BitSet cc;
	public BitSet ir;
	public BitSet mbr;
	public BitSet msr;
	public BitSet mfr;
	public int pc;
	public int mar;
	public int x1;
	public int x2;
	public int x3;
	
	public Register() {
		init();
	}
	
	private void init() {
		r0 = new BitSet(16);
		r1 = new BitSet(16);
		r2 = new BitSet(16);
		r3 = new BitSet(16);
		cc = new BitSet(4);
		ir = new BitSet(16);
		mbr = new BitSet(16);
		msr = new BitSet(16);
		mfr = new BitSet(4);
		pc = 0;
		mar = 0;
		x1 = 0;
		x2 = 0;
		x3 = 0;
	}
	
}
