import java.util.BitSet;

/**Class that defines instruction objects and executes instruction. */

public class Instruction {
	
	private Word value;
		
    private int opcode;
    private int ireg;
    private int index;
    private int instype;
    private int insadd;

	/**
	 * Initializes instruction.
	 */
	public Instruction() {
    	value = new Word();
    	opcode = 0;
    	ireg = 0;
    	index = 0;
    	instype = 0;
    	insadd = 0;
    }

	public Word getValue() {
    	return value;
    }


	public void assignValue(String val) {
    	for (int i = 0; i < 16; i++) {
    		if (val.charAt(i) == '1') value.set(i, true);
    		else value.set(i, false);
    	}
	}

	/**
	 * Fetches instruction from input screen and divides to specific part.
	 *
	 * <pre>
	 * Opcode[0,6]
	 * R[6,8]
	 * IX[8,10]
	 * I[10]
	 * Address[11,16]
	 * </pre>
	 *
	 * @see Decode#binaryToDecimal(String)
	 * @see Register#writeToRegister(String, String, int)
	 */
	public void fetchInstruction() {

    	String ins = value.convertToString();
    	
    	System.out.println(ins);
        // Instruction register 
        // insert fetch instruction from input screen and assingn to variable ir 
    	//fetching various parts of the instructions 
        //operand part of the instruction
    	
        String opbinary = ins.substring(0,6);
        String iregister = ins.substring(6,8);//r
        String iindex = ins.substring(8,10);//ix
        char temp = ins.charAt(10);
        String itype = new StringBuilder().append("").append(temp).toString();
        String iaddress = ins.substring(11,16);//ad
        	
        opcode = Decode.binaryToDecimal(opbinary);// decimal op
        ireg = Decode.binaryToDecimal(iregister);//decimal r
        index = Decode.binaryToDecimal(iindex);// decial ix
        instype = Decode.binaryToDecimal(itype);//decimal i
        insadd = Decode.binaryToDecimal(iaddress);//decimal address
        
        System.out.println(opcode);
        System.out.println(ireg);
        System.out.println(index);
        System.out.println(instype);
        System.out.println(insadd);
        MainApp.myRegisters.writeToRegister("IR", ins, 16);
        
        if (opcode == 1 || opcode == 2 || opcode == 3 ||
        	opcode == 33 || opcode == 34) {
        	execute();
        }
        
        else if (opcode == 8 || opcode == 9 || opcode == 10) {
        	execTransfer();
        }
        
        else if () {
        	execArithmetic();
        }
    }

	/**
	 * Executes instruction.
	 *
	 * <pre>
	 * LDR : Load register with value from the address
	 * STR : Store Address location with the contents of general register
	 * LDA : Load register with the address
	 * LDX : Load Index Register with Contents of Address
	 * STX : Store contents of the Index Register at the Address location
	 * </pre>
	 *
	 * @see Decode#binaryToDecimal(String)
	 * @see Decode#IntegerTo16sBinary(int)
	 * @see Register#writeToRegister(String, String, int)
	 * @see Register#getIXValue(int)
	 * @see Register#getGRValue(int)
	 * @see Register#writeToRegister(String, String, int)
	 * @see Register#writeToGR(int, String)
	 * @see Register#writeToIX(int, String)
	 */
	public void execute() {
    	switch (opcode) {
	    	case 1://LDR r, x, address[,I]
	    		int ldr_ea = 0;
	    		//Calculate EA
	    		if (instype == 0) {
	    			//Direct Addressing
	    			if (index == 0) {
	    				//Get the value stored in insadd
	    				ldr_ea = insadd;
	    			}
	    			else {
	    				//Get the value stored in [ix] + insadd
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				ldr_ea = ixValue + insadd;
	    			}
	    		}
	    		else {
	    			//Indirect Addressing
	    			if (index == 0) {
	    				String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
	    				ldr_ea = Decode.binaryToDecimal(tmp);
	    			}
	    			else {
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				int buffer = ixValue + insadd;
	    				String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
	    				ldr_ea = Decode.binaryToDecimal(tmp);
	    			}
	    		}
	    		//creating 16 bit string of EA  
	    		String ldr_eaStr = Decode.IntegerTo16sBinary(ldr_ea);
	    		//fetching word from memory and converting to string - Getting value at the memory 
	    		String ldr_mValue = MainApp.myMemory.readFromMemory(ldr_ea).convertToString();
	    		//store address into MAR 
	    		MainApp.myRegisters.writeToRegister("MAR", ldr_eaStr, 16);
	    		//Store value into MBR
	    		MainApp.myRegisters.writeToRegister("MBR", ldr_mValue, 16);
	    		//Store value to GR register 
	    		MainApp.myRegisters.writeToGR(ireg, ldr_mValue);
	    		break;
	    		
	    		
	    	case 2: // STR r,x,address
	    		int str_ea = 0;
	    		//Calculate EA
	    		if (instype == 0) {
	    			//Direct addressing
	    			if (index == 0) {
	    				str_ea = insadd;
	    			}
	    			else {
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				str_ea = ixValue + insadd;
	    			}
	    		}
	    		else {
	    			//Indirect addressing
	    			if (index == 0) {
	    				String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
	    				str_ea = Decode.binaryToDecimal(tmp);
	    			}
	    			else {
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				int buffer = ixValue + insadd;
	    				String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
	    				str_ea = Decode.binaryToDecimal(tmp);
	    			}
	    		}
	    		//Set MAR = EA, MBR = [RR], then write to memory
	    		String str_eaStr = Decode.IntegerTo16sBinary(str_ea);
	    		String str_rValue = MainApp.myRegisters.getGRValue(ireg);
	    		MainApp.myRegisters.writeToRegister("MAR", str_eaStr, 16);
	    		MainApp.myRegisters.writeToRegister("MBR", str_rValue, 16);
	    		MainApp.myMemory.writeToMemory(str_ea, str_rValue);
	    		break;
	    		
	    		
	    	case 3: // LDA r ,x,address 
	    		//Load the register with the address (not the content)
	    			    		
	    		int lda_ea = 0;
	    		//Calculate EA
	    		if (instype == 0) {
	    			//Direct Addressing
	    			if (index == 0) {
	    				//Get the value stored in insadd
	    				lda_ea = insadd;
	    			}
	    			else {
	    				//Get the value stored in [ix] + insadd
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				lda_ea = ixValue + insadd;
	    			}
	    		}
	    		else {
	    			//Indirect Addressing
	    			if (index == 0) {
	    				String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
	    				lda_ea = Decode.binaryToDecimal(tmp);
	    			}
	    			else {
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				int buffer = ixValue + insadd;
	    				String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
	    				lda_ea = Decode.binaryToDecimal(tmp);
	    			}
	    		}
	    		//creating 16 bit string of EA  
	    		String lda_eaStr = Decode.IntegerTo16sBinary(lda_ea);
	    		//fetching word from memory and converting to string - Getting value at the memory 
	    		String lda_mValue = MainApp.myMemory.readFromMemory(lda_ea).convertToString();
	    		//store address into MAR 
	    		MainApp.myRegisters.writeToRegister("MAR", lda_eaStr, 16);
	    		//Store value into MBR
	    		MainApp.myRegisters.writeToRegister("MBR", lda_mValue, 16);
	    		//Store address to GR register 
	    		MainApp.myRegisters.writeToGR(ireg, lda_eaStr);
	    		break; 
	    		
	    		
	    	case 33:// LDX x,address 
	    		//Load index register with contents of the  memory location
	    		
	    		int ldx_ea = 0;
	    		//Calculate EA
	    		if (instype == 0) {
	    			//Direct Addressing
	    			//Get the value stored in insadd
	    				ldx_ea = insadd;
	    		}
	    		else {
	    			//Indirect Addressing - 
	    			//temporary variable = string of address
	    				String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
	    				// decimal value of address stores in EA
	    				ldx_ea = Decode.binaryToDecimal(tmp);
	    		}
	    		//creating 16 bit string of EA  
	    		String ldx_eaStr = Decode.IntegerTo16sBinary(ldx_ea);
	    		//fetching word from memory and converting to string - Getting value at the memory 
	    		String ldx_mValue = MainApp.myMemory.readFromMemory(ldx_ea).convertToString();
	    		//store address into MAR 
	    		MainApp.myRegisters.writeToRegister("MAR", ldx_eaStr, 16);
	    		//Store value into MBR
	    		MainApp.myRegisters.writeToRegister("MBR", ldx_mValue, 16);
	    		//Store address to GR register 
	    		int decimalmValue = Decode.binaryToDecimal(ldx_mValue);
	    		MainApp.myRegisters.writeToIX(index, String.valueOf(decimalmValue));
	    		break; 
	    		
	    		
	    	case 34://STX x,address 
	    		//Store the index Register value into the Memory Location
	    		int stx_ea = 0;
	    		//Calculate EA
	    		if (instype == 0) {
	    			//Direct addressing
	    			stx_ea = insadd;
	    		}
	    		else {
	    			//Indirect addressing
	    			String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
	    			stx_ea = Decode.binaryToDecimal(tmp);
	    		}
	    		//Set MAR = EA, MBR = [RR], then write to memory
	    		String stx_eaStr = Decode.IntegerTo16sBinary(stx_ea);
	    		//String stx_rValue = MainApp.myRegisters.getGRValue(ireg);
	    		//Fetching the value of Index Register
	    		int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    		//Convert the value into 16 bit binary
	    		String stx_ixValue=Decode.IntegerTo16sBinary(ixValue);
	    		MainApp.myRegisters.writeToRegister("MAR", stx_eaStr, 16);
	    		MainApp.myRegisters.writeToRegister("MBR", stx_ixValue, 16);
	    		MainApp.myMemory.writeToMemory(stx_ea, stx_ixValue);
	    		break;
	    		 	
	    		

    	}	
    }

	public void execArithmetic() {
		//Arithmetic instruction here
	}
	public void execTransfer() {
		//Transfer instruction here
	}
}