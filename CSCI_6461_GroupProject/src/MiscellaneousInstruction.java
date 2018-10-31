
public class MiscellaneousInstruction extends Instruction{
	private int opcode;
	private int haltcode;
	private int trapcode;
	
	public MiscellaneousInstruction() {
    	super.value = new Word();
    	opcode = -1;
    	haltcode = -1;
    	trapcode = -1;
 		
    }
	
	public void fetchInstruction() {
    	String ins = value.convertToString();
    	System.out.println(ins);
    	
        // Instruction register 
        // insert fetch instruction from input screen and assingn to variable ir 
    	//fetching various parts of the instructions 
        //operand part of the instruction
        String opbinary = ins.substring(0,6);
        opcode = Decode.binaryToDecimal(opbinary);
        
        if (opcode == 0) {
        	String haltStr = ins.substring(10,16);
        	haltcode = Decode.binaryToDecimal(haltStr);
        }
        else if (opcode == 30) {
        	String trapStr = ins.substring(12,16);
        	trapcode = Decode.binaryToDecimal(trapStr);
        }
        
        MainApp.myRegisters.writeToRegister("IR", ins, 16);
	}
	
	public void execute() {
    	switch (opcode) {
    		case 0:
    			MainApp.myClock.setFlag(false);
    			break;
    		case 30:
    			//trap
    			if (trapcode == 0) {
    				
    			}
    			else if (trapcode == 1) {
    				
    			}
    			else if (trapcode == 2) {
    				
    			}
    			break;
    	}
	}
}
