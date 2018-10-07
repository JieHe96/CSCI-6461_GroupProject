
public class Clock {
	private int cycle;
	private boolean flag;
	
	public Clock() {
		cycle = 0;
		flag = false;
	}
	
	public int getClockCycle() { return cycle; };
	
	public void singleRun(int index) {
		if (!MainApp.myInstructionList.getAddrList().isEmpty()) flag = true;
		
		if (flag) {
			MainApp.myInstructionList.runSingleInstruction(index);
			cycle++;
		}
	}
	
	public void runFromStart() {
		int i = 0;
		while (flag) {
			MainApp.myInstructionList.runSingleInstruction(i);
			cycle++;
			//MainApp.frame.updateUI();
		}
		reset();
	}
	
	public void setFlag(boolean f) {
		flag = f;
	}
	
	public void reset() {
		cycle = 0;
		flag = false;
	}
	
	public boolean isReady() {
		return flag;
	}
	
	public void resume() {
		if (!MainApp.myInstructionList.getAddrList().isEmpty()) flag = true;
	}
}
