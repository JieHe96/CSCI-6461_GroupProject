import java.util.Vector;

public class IOInstruction extends Instruction{
//private Word value;
	
    private int opcode;
    private int ireg;
    private int deviceID;

	/**
	 * Initializes instruction.
	 */
	public IOInstruction() {
    	super.value = new Word();
    	opcode = 0;
    	ireg = 0;
    	deviceID = 0;
    }

	public void fetchInstruction() {

    	String ins = value.convertToString();
    	
    	//System.out.println(ins);
        // Instruction register 
        // insert fetch instruction from input screen and assingn to variable ir 
    	//fetching various parts of the instructions 
        //operand part of the instruction
    	
        String opbinary = ins.substring(0,6);
        String iregister = ins.substring(6,8);//r
        String ideciveID = ins.substring(11,16);//ad
        	
        opcode = Decode.binaryToDecimal(opbinary);// decimal op
        ireg = Decode.binaryToDecimal(iregister);//decimal r
        deviceID = Decode.binaryToDecimal(ideciveID);//decimal address
        
        //System.out.println(opcode);
        //System.out.println(ireg);
        //System.out.println(deviceID);
        MainApp.myRegisters.writeToRegister("IR", ins, 16);
        
    }
	
	public void execute() {
		System.out.print(opcode);
		switch (opcode) {
			case 49:
				if (deviceID == 0) {
					String keyboardStr = MainApp.frame.getKeyboardStr();
					MainApp.myDevice.setKeyboard(keyboardStr);
					String buffStr = null;
					String resStr = null;
					try { 
						// checking valid integer using parseInt() method 
				        int buff = Integer.parseInt(keyboardStr); 
				        //buffStr = String.format("%16s", keyboardStr).replace(" ", "0");
				        buffStr = Decode.IntegerTo16sBinary(buff);
				        MainApp.myRegisters.setCharMap(ireg, 0);
				    }  
				    catch (NumberFormatException e) { 
				    	int buffInt;
				    	if (keyboardStr.equals("/n")) {
				    		resStr = keyboardStr.substring(2);
				    		buffInt = 10;
				    	}
				    	else {
				    		resStr = keyboardStr.substring(1);
					    	char buff = keyboardStr.charAt(0);
					    	buffInt = buff;
				    	}
				    	System.out.println("===== " + buffInt);
				    	buffStr = Decode.IntegerTo16sBinary(buffInt);
				    	MainApp.myRegisters.setCharMap(ireg, 1);
				    }
					//remove first char
					
					MainApp.frame.setKeyboard(resStr);
					//String buffStr = String.format("%16s", keyboardStr).replace(" ", "0");
					MainApp.myRegisters.writeToGR(ireg, buffStr);
					
				}
				break;
			case 50:
				String buff = MainApp.myRegisters.getGRValue(ireg);
				if (MainApp.myRegisters.checkIsChar(ireg) == 1) {
					int buffInt = Decode.binaryToDecimal(buff);
					buff = Character.toString((char) buffInt);
				}
				else {
					buff = Integer.toString(Decode.binaryToDecimal(buff));
				}
				MainApp.myDevice.setPrinter(buff);
				MainApp.frame.setPrinter(buff);
				break;
		}
	}
}
