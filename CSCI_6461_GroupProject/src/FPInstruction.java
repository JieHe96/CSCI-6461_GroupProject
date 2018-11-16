
public class FPInstruction extends Instruction{
	
    private int opcode;
    private int freg;
    private int index;
    private int instype;
    private int insadd;
    
	public FPInstruction() {
    	super.value = new Word();
    	opcode = 0;
    	freg = 0;
    	index = 0;
    	instype = 0;
    	insadd = 0;
    }
}
