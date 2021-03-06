import java.util.*;
import java.util.stream.IntStream;

/**
 * Class that defines registers and write and get register value
 */
public class Register {
	/**
	 * {"R0:", "R1:", "R2:", "R3:", "PC:", "CC:", "IR:", 
	 * "MAR:", "MBR:", "MSR:", "MFR:", "X1:", "X2:", "X3:", 
	 * "FR0:", "FR1:"};
	 */
	private Map<String, BitSet> registerMap;
	private Map<String, Integer> checkCharMap;
	/** Initializes register */
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
		registerMap.put("FR0", new BitSet(16));
		registerMap.put("FR1", new BitSet(16));
		
		//use to check if data in register is character
		checkCharMap = new HashMap<String, Integer> ();
		checkCharMap.put("R0", 0);
		checkCharMap.put("R1", 0);
		checkCharMap.put("R2", 0);
		checkCharMap.put("R3", 0);
	}
	/** Used to write to the register in a 12/ 16 Format  bit*/
	public void writeToRegister(String name, String value, int length) {
		if (registerMap.containsKey(name)) {
			if (name == "X1" || name == "X2" || name == "X3") {
				int decNum = Decode.ToDecimal(value);
				String biNum = Decode.IntegerTo16sBinary(decNum);
				value = biNum;
			}
			else if (name == "PC") {
				int decNum = Decode.ToDecimal(value);
				String biNum = Decode.IntegerTo12sBinary(decNum);
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
	 /** This function is used to return 
	  * the value of the register in string  format */
	public String getRegister(String name, boolean isDecimal) {
		char[] chars;
		if (name == "PC") {
			chars = new char[12];
		}
		else if(name == "CC" || name == "MFR")
		{
			chars = new char[4];
		}
		else chars = new char[16];
		
		Arrays.fill(chars, '0');
		for (int i = 0; i < registerMap.get(name).length(); i++) {
			if (registerMap.get(name).get(i) == true) {
				chars[i] = '1';
			}
		}
		String str = new String(chars);
		if (isDecimal) {
			int buff = Decode.binaryToDecimal(str);
			str = String.valueOf(buff);
		}
		return str;
	}
	
	public Map<String, BitSet> getRegisterMap() {
		return registerMap;
	}

	/**
	 * Get the value of general purpose registers
	 *
	 * @param num the serial number of general registers
	 * @return
	 */
	public String getGRValue(int num) {
		switch (num) {
			case 0:
				return getRegister("R0", false);
			case 1:
				return getRegister("R1", false);
			case 2:
				return getRegister("R2", false);
			case 3:
				return getRegister("R3", false);
		}
		return null;
	}

	/**
	 * Write to general purpose registers
	 *
	 * @param num the serial number of general registers
	 * @param value the value of general registers
	 */
	public void writeToGR(int num, String value) {
		switch (num) {
			case 0:
				writeToRegister("R0", value, 16);
				break;
			case 1:
				writeToRegister("R1", value, 16);
				break;
			case 2:
				writeToRegister("R2", value, 16);
				break;
			case 3:
				writeToRegister("R3", value, 16);
				break;
		}
	}

	/**
	 * Get the value of index registers
	 *
	 * @param num the serial number of index registers
	 * @return
	 */
	public String getIXValue(int num) {
		switch (num) {
		case 1:
			return getRegister("X1", true);
		case 2:
			return getRegister("X2", true);
		case 3:
			return getRegister("X3", true);
	}
	return null;
	}

	/**
	 * Write to index registers
	 *
	 * @param num the serial number of index registers
	 * @param value the value of general registers
	 */
	public void writeToIX(int num, String value) {
		switch (num) {
			case 1:
				writeToRegister("X1", value, 16); 
				break;
			case 2:
				writeToRegister("X2", value, 16);
				break;
			case 3:
				writeToRegister("X3", value, 16);
				break;
		}
	}


	
	public void setCharMap(int num, int flag) {
		switch (num) {
			case 0:
				checkCharMap.put("R0", flag);
				break;
			case 1:
				checkCharMap.put("R1", flag);
				break;
			case 2:
				checkCharMap.put("R2", flag);
				break;
			case 3:
				checkCharMap.put("R3", flag);
				break;
		}
	}
	
	public int checkIsChar(int num) {
		switch (num) {
		case 0:
			return checkCharMap.get("R0");
		case 1:
			return checkCharMap.get("R1");
		case 2:
			return checkCharMap.get("R2");
		case 3:
			return checkCharMap.get("R3");
		}
		return 0;
	}
	
	public void writeToFP(int num, char sign, String exponent, String mantissa) {
		BitSet buffer = new BitSet(16);
		if (sign == '0') buffer.set(0, false);
		else buffer.set(0, true);
		for (int i = 0; i < exponent.length(); i++) {
			if (exponent.charAt(i) == '1') buffer.set(i+1, true);
    		else buffer.set(i+1, false);
		}
		for (int i = 0; i < mantissa.length(); i++) {
			if (mantissa.charAt(i) == '1') buffer.set(i+8, true);
    		else buffer.set(i+8, false);
		}
		if (num == 0) registerMap.put("FR0", buffer);
		else registerMap.put("FR1", buffer);
	}

}
