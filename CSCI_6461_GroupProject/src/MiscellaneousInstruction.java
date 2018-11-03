
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
    	//System.out.println(ins);
    	
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
    			if (trapcode > 1) {
    				MachineFault opFault = new MachineFault();
    				opFault.handleFault(1);
    				System.out.println("Machine Fault: Illegal Trap Code.");
    				return;
    			}
    			//write current PC value to memory location 4
				String currPC = MainApp.myRegisters.getRegister("PC", true);
				String currPC16s = Decode.IntegerTo16sBinary(Integer.parseInt(currPC)+1);
				System.out.println("currPC: " + currPC16s);
				MainApp.myMemory.setFlag(false);
				MainApp.myMemory.writeToMemory(2, currPC16s);
				MainApp.myMemory.setFlag(true);
				
				
				
				//execute machine fault routine and return to PC+1
				if (trapcode == 0) {
					//read value from memory location 1 to PC
					String addr = MainApp.myMemory.readFromMemory(0).convertToString();
					int pcNum = Decode.binaryToDecimal(addr);
					String pcStr = Integer.toString(pcNum);
					MainApp.myRegisters.writeToRegister("PC", pcStr, 12);
					MainApp.myInstructionList.exeTrapIns1();
				}
				else if (trapcode == 1) {
					//read value from memory location 1 to PC
					String addr = MainApp.myMemory.readFromMemory(0).convertToString();
					int pcNum = Decode.binaryToDecimal(addr) + 13;
					String pcStr = Integer.toString(pcNum);
					MainApp.myRegisters.writeToRegister("PC", pcStr, 12);
					MainApp.myInstructionList.exeTrapIns2();
				}
				
				String addr1 = MainApp.myMemory.readFromMemory(2).convertToString();
				int pcNum1 = Decode.binaryToDecimal(addr1);
				String pcStr1 = Integer.toString(pcNum1);
				MainApp.myRegisters.writeToRegister("PC", pcStr1, 12);
				MainApp.frame.updateUI();
    			break;
    	}
	}
}
