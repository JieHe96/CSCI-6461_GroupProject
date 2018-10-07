import java.util.Vector;

public class Device {
	private String keyboard;
	private Vector<String> printer;
	
	public Device() {
		keyboard = "";
		printer = new Vector<String> ();
	}
	
	public void setKeyboard(String str) {
		keyboard = str;
	}
	
	public void setPrinter(String str) {
		printer.add(str);
	}
	
	public Vector<String> getPrinter() {
		return printer;
	}
	
	
}
