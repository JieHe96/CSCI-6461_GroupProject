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
		r0 = 0;
		r1 = 0;
		r2 = 0;
		r3 = 0;
		pc = 0;
		cc = 0;
		ir = 0;
		mar = 0;
		mbr = 0;
		msr = 0;
		mfr = 0;
		x1 = 0;
		x2 = 0;
		x3 = 0;
	}
	
}
