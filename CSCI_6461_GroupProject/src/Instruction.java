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
    	
        String opbinary =ins.substring(0,5);
        String iregister =ins.substring(6,7);//r
        String iindex =ins.substring(8,9);//ix
        String itype =ins.substring(10);//i
        String iaddress =ins.substring(11,15);//ad
        	
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


    // convert input into object values using switch case 

}