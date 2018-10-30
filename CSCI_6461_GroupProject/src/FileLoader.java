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
            while ((ascii = inputStream.read()) != -1) 
            {
			System.out.println(ascii); // ascii is the ascii value and char(ascii) is the text. 
			char inChar = (char)ascii; // inChar is the char value 
			
			String inBin = Decode.IntegerTo16sBinary(ascii); // to 16 bit binary 
			System.out.print(ascii + "\t" + inChar + "\t "+ inBin ); // pRinting to console
			MainApp.myMemory.writeToMemory(index, inBin);		// Storing to memory location 
			index++; // incrementing index for next storage
			txt_len++;
			}
            
            System.out.print("The total len" + txt_len);
            
	}            
        } 
     
        

