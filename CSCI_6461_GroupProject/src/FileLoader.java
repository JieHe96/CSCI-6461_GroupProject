import java.io.*;
import java.util.Scanner;

/**
 * This file fetches characters from the input file 
 *
 */
public class FileLoader {

	File file;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public FileLoader(File f) {
		file = f;
	}

	public void progStore() throws IOException {
		
		FileReader inputStream = new FileReader(file);
        int index= 350; // memory location where text chars are being stored
        int txt_len = 0; // length of the entire text
		int ascii;
		String element;
		
		while ((ascii = inputStream.read()) != -1) {
			//System.out.println(ascii); // ascii is the ascii value and char(ascii) is the text. 
			char inChar = (char)ascii; // inChar is the char value 
			String inBin = Decode.IntegerTo16sBinary(ascii); // to 16 bit binary 
			// Printing to console
			System.out.println(ascii + "\t" + inChar + "\t "+ inBin + "\t stored at  " + index + "\t " ); // pRinting to console
			// writing to memory // Storing to memory location 
			MainApp.myMemory.writeToMemory(index, inBin);
			element=Character.toString(inChar);
			// printing to IO console
			MainApp.frame.setPrinter(element); 
			// checking or end of line
			if(inChar=='.')
				MainApp.frame.setPrinter("\n");
			// incrementing index for next storage
			index++; 
			txt_len++;
		}
		// Asking user for Input 
		MainApp.frame.setPrinter("\n  Enter search element in keyboard  \n ");// Storing to memory location
        System.out.print(" \t The total len" + txt_len);
	}            
} 





     
        

