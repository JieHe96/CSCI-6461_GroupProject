import java.util.Scanner;
public class Decode 
{

    /**
     * converts string type-data to integer
     *
     * @param str
     * @return integer of str
     */
	public static int ToDecimal(String str) {
        return (Integer.parseInt(str));
    }

    /**
     * converts binary to decimal
     *
     * @param str
     * @return	decimal value of str
     */
    public static int binaryToDecimal(String str) {
		return (Integer.parseInt(str, 2));
	}

    /**
     * converts a int data-type to 16bits binary string
     *
     * @param x
     * @return	binary string of x
     */
    public static String IntegerTo16sBinary(int x) {
    	return String.format("%16s", Integer.toBinaryString(x)).replace(" ", "0");
    }

    /**
     * converts a int data-type to 12bits binary string
     *
     * @param x
     * @return	binary string of x
     */
    public static String IntegerTo12sBinary(int x) {
    	return String.format("%12s", Integer.toBinaryString(x)).replace(" ", "0");
    }
}
   
