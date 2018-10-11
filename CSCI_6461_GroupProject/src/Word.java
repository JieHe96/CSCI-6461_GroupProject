import java.util.Arrays;
import java.util.BitSet;

//Word class extend the BitSet type

/**
 * Word class extends the BitSet type
 */
public class Word extends BitSet{
	private boolean isChar = false;
	
	public Word() {
		super(16);
	}

	public String convertToString() {
		char[] chars = new char[16];
		Arrays.fill(chars, '0');
		for (int i = 0; i < 16; i++) {
			if (this.get(i) == true) {
				chars[i] = '1';
			}
		}
		String str = new String(chars);
		return str;
	}
	
	public void write(String value) {
		for (int i = 0; i < 16; i++) {
    		if (value.charAt(i) == '1') this.set(i, true);
    		else this.set(i, false);
    	}
	}
}
