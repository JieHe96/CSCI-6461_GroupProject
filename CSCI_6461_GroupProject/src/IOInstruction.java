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
    	
    	System.out.println(ins);
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
        
        System.out.println(opcode);
        System.out.println(ireg);
        System.out.println(deviceID);
        MainApp.myRegisters.writeToRegister("IR", ins, 16);
        
    }
	
	public void execute() {
		System.out.print(opcode);
		switch (opcode) {
			case 49:
				if (deviceID == 0) {
					String keyboardStr = MainApp.frame.getKeyboardStr();
					MainApp.myDevice.setKeyboard(keyboardStr);
					String buffStr = String.format("%16s", keyboardStr).replace(" ", "0");
					MainApp.myRegisters.writeToGR(ireg, buffStr);
				}
				break;
			case 50:
				String buff = MainApp.myRegisters.getGRValue(ireg);
				MainApp.myDevice.setPrinter(buff);
				MainApp.frame.setPrinter(buff);
				break;
		}
	}
}
