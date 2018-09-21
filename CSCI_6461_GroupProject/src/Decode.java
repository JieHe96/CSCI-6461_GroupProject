import java.util.Scanner;
public class Decode 
{
    /**
     * fetch certain bits of a 16bits number
     * @param value 16bits data
     * @param start high bit
     * @param end	low bit
     * @return		value of value[start:end]
     */

    /**gettin the values from the instruction set */ 
	
	public static int ToDecimal(String str) {
        return (Integer.parseInt(str));
    }
	
	public static int binaryToDecimal(String str) {
		return (Integer.parseInt(str, 2));
	}
    
    public static String IntegerTo16sBinary(int x) {
    	return String.format("%16s", Integer.toBinaryString(x)).replace(" ", "0");
    }
    
    public static String IntegerTo12sBinary(int x) {
    	return String.format("%12s", Integer.toBinaryString(x)).replace(" ", "0");
    }
}
   
