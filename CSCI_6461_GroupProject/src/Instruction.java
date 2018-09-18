/**Class that defines instruction objects  */ 

public class Instruction {
	
	private Decode de;
	
    private int opcode;
    private int ireg;
    private int index;
    private int instype;
    private int insadd;
    
    public Instruction() {
    	de = new Decode();
    	opcode = 0;
    	ireg = 0;
    	index = 0;
    	instype = 0;
    	insadd = 0;
    }
    
    public void FetchInstruction(String ins) {

        // Instruction register 
        // insert fetch instruction from input screen and assingn to variable ir 
    	//fetching various parts of the instructions 
        //operand part of the instruction
    	
        String opbinary =ins.substring(0,6);
        String iregister =ins.substring(6,8);//r
        String iindex =ins.substring(8,10);//ix
        char temp = ins.charAt(10);
        String itype = new StringBuilder().append("").append(temp).toString();
        String iaddress =ins.substring(11,16);//ad
        	
        opcode = de.ToDecimal(opbinary);// decimal op
        ireg = de.ToDecimal(iregister);//decimal r
        index = de.ToDecimal(iindex);// decial ix
        instype = de.ToDecimal(itype);//decimal i
        insadd = de.ToDecimal(iaddress);//decimal address
        
        System.out.println(opcode);
        System.out.println(ireg);
        System.out.println(index);
        System.out.println(instype);
        System.out.println(insadd);
        	
    }
    
    public void Execute() {
    	switch (opcode) {
	    	case 1://LDR r,x,address
	    		
	    		break;
	    	case 2://STR r,x,address
	    		break;
	    	case 3://LDA r,x,address
	    		break;
	    	case 33://LDX x,address
	    		break;
	    	case 34://STX X,address
	    		break;
    	}
    }


    // convert input into object values using switch case 

}