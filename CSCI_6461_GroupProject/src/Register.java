import java.util.*;

public class Register {
	/**
	 * {"R0:", "R1:", "R2:", "R3:", "PC:", "CC:", "IR:", 
	 * "MAR:", "MBR:", "MSR:", "MFR:", "X1:", "X2:", "X3:"};
	 */
	public int r0;
	public int r1;
	public int r2;
	public int r3;
	public int pc;
	public int cc;
	public int ir;
	public int mar;
	public int mbr;
	public int msr;
	public int mfr;
	public int x1;
	public int x2;
	public int x3;
	
	public Register() {
		pc = 0;
		r0 = 1;
		r1 = 2;
		r2 = 3;
		r3 = 4;
		cc = 5;
		ir = 6;
		mar = 7;
		mbr = 8;
		msr = 9;
		mfr = 10;
		x1 = 11;
		x2 = 12;
		x3 = 13;
	}
	
}
