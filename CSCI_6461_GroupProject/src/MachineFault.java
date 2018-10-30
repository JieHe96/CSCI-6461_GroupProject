
public class MachineFault {
	private int faultID;
	
	public MachineFault () {
		faultID = -1;
	}
	
	public void handleFault(int id) {
		faultID = id;
		switch (faultID) {
			case 0:
				MainApp.myRegisters.writeToRegister("MFR", "0001", 4);
				//MainApp.frame.setPrinter("Machine Fault: Illegal Memory Address to Reserved Locations");
				break;
			case 1:
				MainApp.myRegisters.writeToRegister("MFR", "0010", 4);
				break;
			case 2:
				MainApp.myRegisters.writeToRegister("MFR", "0100", 4);
				//MainApp.frame.setPrinter("Machine Fault: Illegal Operation Code");
				break;
			case 3:
				MainApp.myRegisters.writeToRegister("MFR", "1000", 4);
				//MainApp.frame.setPrinter("Machine Fault: Illegal Memory Address beyond 2048");
				break;
		}
		String currPC = MainApp.myRegisters.getRegister("PC", true);
		System.out.println("currPC: " + currPC);
		String addr = MainApp.myMemory.readFromMemory(1).convertToString();
		int pcNum = Decode.binaryToDecimal(addr);
		String pcStr = Integer.toString(pcNum);
		//String value = MainApp.myMemory.readFromMemory(pcNum).convertToString();
		//MainApp.myInstructionList.addMachineFaultIns(pcNum, value);
		MainApp.myRegisters.writeToRegister("PC", pcStr, 12);
		MainApp.frame.updateUI();
		MainApp.myInstructionList.exeMachineFaultIns();
	}
	
}
