import java.util.BitSet;

/**Class that defines instruction objects  */ 

public class Instruction {
	
	private Word value;
		
    private int opcode;
    private int ireg;
    private int index;
    private int instype;
    private int insadd;
    
    public Instruction() {
    	value = new Word();
    	opcode = 0;
    	ireg = 0;
    	index = 0;
    	instype = 0;
    	insadd = 0;
    }
    
    
	public void assignValue(String val) {
    	for (int i = 0; i < 16; i++) {
    		if (val.charAt(i) == '1') value.set(i, true);
    		else value.set(i, false);
    	}
	}
    
    public void fetchInstruction() {

    	String ins = value.convertToString();
    	
    	System.out.println(ins);
        // Instruction register 
        // insert fetch instruction from input screen and assingn to variable ir 
    	//fetching various parts of the instructions 
        //operand part of the instruction
    	
        String opbinary = ins.substring(0,6);
        String iregister = ins.substring(6,8);//r
        String iindex = ins.substring(8,10);//ix
        char temp = ins.charAt(10);
        String itype = new StringBuilder().append("").append(temp).toString();
        String iaddress = ins.substring(11,16);//ad
        	
        opcode = Decode.binaryToDecimal(opbinary);// decimal op
        ireg = Decode.binaryToDecimal(iregister);//decimal r
        index = Decode.binaryToDecimal(iindex);// decial ix
        instype = Decode.binaryToDecimal(itype);//decimal i
        insadd = Decode.binaryToDecimal(iaddress);//decimal address
        
        System.out.println(opcode);
        System.out.println(ireg);
        System.out.println(index);
        System.out.println(instype);
        System.out.println(insadd);
        MainApp.myRegisters.writeToRegister("IR", ins, 16);
    }
    
    public void execute() {
    	switch (opcode) {
	    	case 1://LDR r, x, address[,I]
	    		if (instype == 0) {
	    			
	    		}
	    		else {
	    			
	    		}
	    		break;
	    	case 2: // STR r,x,address
	    		System.out.println("in");
	    		int ea = 0;
	    		//Calculate EA
	    		if (instype == 0) {
	    			if (index == 0) {
	    				ea = insadd;
	    			}
	    			else {
	    				ea = index + insadd;
	    			}
	    		}
	    		else {
	    			if (index == 0) {
	    				//String addr = MainApp.myMemory.readFromMemory(insadd).convertToString();
	    				
	    			}
	    			else {
	    				
	    			}
	    		}
	    		//Set MAR = EA
	    		String eaStr = Decode.IntegerTo16sBinary(ea);
	    		String rValue = MainApp.myRegisters.getGRValue(ireg);
	    		MainApp.myRegisters.writeToRegister("MAR", eaStr, 16);
	    		MainApp.myRegisters.writeToRegister("MBR", rValue, 16);
	    		break;
	    	case 3: // LDA r ,x,address 
	    		//Load the register with the address (not the content) 
	    		break;
	    	case 33:// LDX x,address 
	    		//Load index register with contents of the  memory location
	    		break;
	    	case 34://STX x,address 
	    		//Store the index Register value into the Memory Location
	    		break;
	    	case 8:// JZ r,x,address :- Jump if Zero
	    			// if c(r)=0 -> PC=EA else PC=PC+1
	    		break;
	    	case 9: //JNE r,x,address : Jump if not equal
	    		// if c(r)!=0 -> PC=EA else PC=PC+1
	    		break;
	    	case 10: // JCC cc,x,address : Jump if Condition Code 
	    		// if cc bit=1 , PC=EA else PC=PC+1
	    		break;
    	}	
    }

}