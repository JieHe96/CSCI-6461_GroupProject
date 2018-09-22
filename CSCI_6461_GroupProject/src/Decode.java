import java.util.Scanner;

/**Class that convert data type and hexadecimal conversion  */

public class Decode 
{

    /**
     * Converts string type-data to integer
     *
     * @param str a string type data
     * @return integer of str
     */
	public static int ToDecimal(String str) {
        return (Integer.parseInt(str));
    }

    /**
     * Converts binary to decimal
     *
     * @param str a string type data
     * @return	decimal value of str
     */
    public static int binaryToDecimal(String str) {
		return (Integer.parseInt(str, 2));
	}

    /**
     * Converts a int data-type to 16 bits binary string
     *
     * @param x a int type data
     * @return	16 bits binary string of x
     */
    public static String IntegerTo16sBinary(int x) {
    	return String.format("%16s", Integer.toBinaryString(x)).replace(" ", "0");
    }

    /**
     * Converts a int data-type to 12 bits binary string
     *
     * @param x a int type data
     * @return	12 bits binary string of x
     */
    public static String IntegerTo12sBinary(int x) {
    	return String.format("%12s", Integer.toBinaryString(x)).replace(" ", "0");
    }
}
   
