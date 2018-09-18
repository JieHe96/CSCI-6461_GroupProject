import java.util.*;

public class Register {
	/**
	 * {"R0:", "R1:", "R2:", "R3:", "PC:", "CC:", "IR:", 
	 * "MAR:", "MBR:", "MSR:", "MFR:", "X1:", "X2:", "X3:"};
	 */
	public short r0;
	public short r1;
	public short r2;
	public short r3;
	public short pc;
	public short cc;
	public short ir;
	public short mar;
	public short mbr;
	public short msr;
	public short mfr;
	public short x1;
	public short x2;
	public short x3;
	public short blank;
	
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
		blank = 14;
	}
	
}
