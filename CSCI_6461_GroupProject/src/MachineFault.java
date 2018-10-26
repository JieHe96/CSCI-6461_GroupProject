
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
				break;
			case 1:
				MainApp.myRegisters.writeToRegister("MFR", "0010", 4);
				break;
			case 2:
				MainApp.myRegisters.writeToRegister("MFR", "0100", 4);
				break;
			case 3:
				MainApp.myRegisters.writeToRegister("MFR", "1000", 4);
				break;
		}
		//MainApp.frame.updateUI();
	}
}
