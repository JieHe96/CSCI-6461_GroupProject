import java.io.*;
import java.util.Scanner;

/**
 * This file fetches characters from the input file 
 *
 */
public class program2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
			
	public void prog_store()
	{
		
		FileReader inputStream = null;
        int index= 350; // memory location where text chars are being stored
        int txt_len = 0; // length of the entire text
        
            inputStream = new FileReader("C:\\Users\\karti\\OneDrive\\Desktop\\test.txt");
            
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
     
        

