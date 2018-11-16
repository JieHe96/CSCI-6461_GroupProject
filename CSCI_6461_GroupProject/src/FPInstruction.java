
public class FPInstruction extends Instruction{
	
    private int opcode;
    private int freg;
    private int index;
    private int instype;
    private int insadd;
    
	// constructor 
    public FPInstruction() {
    	super.value = new Word();
    	opcode = 0;
    	freg = 0;
    	index = 0;
    	instype = 0;
    	insadd = 0;
    }
	
    public void fetchInstruction() {
		String ins = value.convertToString();
		System.out.println(ins);
		

		String opbinary = ins.substring(0,6);
		opcode = Decode.binaryToDecimal(opbinary);// decimal op
		
		if(opcode == 31)
		{
		
		}
		else 
		{
			String freg_bin = ins.substring(6,8);//r
			String iindex = ins.substring(8,10);//ix
			char temp = ins.charAt(10);
			String itype = new StringBuilder().append("").append(temp).toString();
			String iaddress = ins.substring(11,16);//address
			
		}
		
		
    }
	
}
