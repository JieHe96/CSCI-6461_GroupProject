import java.util.*;
import java.util.stream.IntStream;

public class Register {
	/**
	 * {"R0:", "R1:", "R2:", "R3:", "PC:", "CC:", "IR:", 
	 * "MAR:", "MBR:", "MSR:", "MFR:", "X1:", "X2:", "X3:"};
	 */
	private Map<String, BitSet> registerMap;
	
	public Register() {
		init();
	}
	
	private void init() {
		registerMap = new HashMap<String, BitSet> ();
		
		registerMap.put("PC", new BitSet(12));
		registerMap.put("MAR", new BitSet(16));
		registerMap.put("IR", new BitSet(16));
		registerMap.put("MBR", new BitSet(16));
		registerMap.put("CC", new BitSet(4));
		registerMap.put("MFR", new BitSet(4));
		registerMap.put("MSR", new BitSet(16));
		registerMap.put("R0", new BitSet(16));
		registerMap.put("R1", new BitSet(16));
		registerMap.put("R2", new BitSet(16));
		registerMap.put("R3", new BitSet(16));
		registerMap.put("X1", new BitSet(16));
		registerMap.put("X2", new BitSet(16));
		registerMap.put("X3", new BitSet(16));
		
		System.out.println(registerMap.get("R0").length());
	}
	
	public void writeToRegister(String name, String value, int length) {
		if (registerMap.containsKey(name)) {
			if (name == "X1" || name == "X2" || name == "X3") {
				int decNum = Decode.ToDecimal(value);
				String biNum = Decode.IntegerTo16sBinary(decNum);
				value = biNum;
			}
			BitSet buffer = new BitSet(length);
			for (int i = 0; i < length; i++) {
				if (value.charAt(i) == '1') buffer.set(i, true);
	    		else buffer.set(i, false);
			}
			registerMap.put(name, buffer);
		}
	}
	
	public String getRegister(String name) {
		char[] chars = new char[16];
		Arrays.fill(chars, '0');
		for (int i = 0; i < registerMap.get(name).length(); i++) {
			if (registerMap.get(name).get(i) == true) {
				chars[i] = '1';
			}
		}
		String str = new String(chars);
		return str;
	}
	
	public Map<String, BitSet> getRegisterMap() {
		return registerMap;
	}
	
}
