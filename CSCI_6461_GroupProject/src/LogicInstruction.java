public class LogicInstruction extends Instruction{
	//private Word value;
	
    private int opcode;
    private int ireg;
    private int index;
    private int instype;
    private int insadd;
	private int rx1;
	private int ry1;
	private int type;
	private int count;
	private int side;

	/**
	 * Initializes instruction.
	 */
	public LogicInstruction() {
    	super.value = new Word();
    	opcode = 0;
    	ireg = 0;
    	index = 0;
    	instype = 0;
    	insadd = 0;
    	rx1 = 0;
		ry1 = 0;
		type = 0;
		count = 0;
		side = 0;
		
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
        opcode = Decode.binaryToDecimal(opbinary);// decimal op
        
        
        if(opcode == 1 ||opcode ==  2 || opcode == 3|| opcode == 33 ||opcode ==  34 )
        {
        
	        String iregister = ins.substring(6,8);//r
	        String iindex = ins.substring(8,10);//ix
	        char temp = ins.charAt(10);
	        String itype = new StringBuilder().append("").append(temp).toString();
	        String iaddress = ins.substring(11,16);//ad
	        	
	        ireg = Decode.binaryToDecimal(iregister);//decimal r
	        index = Decode.binaryToDecimal(iindex);// decial ix
	        instype = Decode.binaryToDecimal(itype);//decimal i
	        insadd = Decode.binaryToDecimal(iaddress);//decimal address
	        
	        //System.out.println(opcode);
	        //System.out.println(ireg);
	        //System.out.println(index);
	        //System.out.println(instype);
	        //System.out.println(insadd);
        }
        else if(opcode == 18 ||opcode ==  19 ||opcode ==  20 ||opcode ==  21)
        {
        	String str_rx = ins.substring(6, 8); // Rx
			String str_ry = ins.substring(8, 10); // Rx
			
			rx1 = Decode.binaryToDecimal(str_rx);//decimal RX
			ry1 = Decode.binaryToDecimal(str_ry);// decial RY
			
			//System.out.println(opcode);
			//System.out.println(rx1);
			//System.out.println(ry1);
        }
        // shift instructions
        else if(opcode== 25 || opcode== 26 )
    	{
    		String str_r = ins.substring(6, 8);
    		char temp = ins.charAt(8);
    		String str_type = new StringBuilder().append("").append(temp).toString();
    		char temp1 = ins.charAt(9);
    		String str_side = new StringBuilder().append("").append(temp1).toString();
    		String str_count = ins.substring(12,16);//Count
    		
    		ireg = Decode.binaryToDecimal(str_r);//decimal R
    		type = Decode.binaryToDecimal(str_type);//decimal Type
    		count  = Decode.binaryToDecimal(str_count);//decimal Type
    		side = Decode.binaryToDecimal(str_side);//decimal Type
    		
    		
    		//System.out.println(opcode);
    		//System.out.println(ireg);
    		//System.out.println(type);
    		//System.out.println(side);
    		//System.out.println(count);	
    	}
    	
        MainApp.myRegisters.writeToRegister("IR", ins, 16);
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
	    	
	    	case 18: // TRR
	    		//Get value of Rx 	
 	    		String str_rxValue = MainApp.myRegisters.getGRValue(rx1);
				//Rx value in decimal
				int regxvalue=Decode.binaryToDecimal(str_rxValue);
								//Get value of RY
				String str_ryValue = MainApp.myRegisters.getGRValue(ry1);
				//Ry value in decimal
				// Check for equivalence
				int regyvalue=Decode.binaryToDecimal(str_ryValue);
				String res=MainApp.myRegisters.getRegister("CC",false);
				if(regxvalue==regyvalue)
				{
					res=res.substring(0,3)+ "1";
					MainApp.myRegisters.writeToRegister("CC",res,4);
				}
				else
				{
					res=res.substring(0,3)+ "0";
					MainApp.myRegisters.writeToRegister("CC",res,4);
				}	
				//store result in Rx
				//MainApp.myRegisters.writeToGR(rx1,strResult2);

			    		
				break;
			case 19: // AND
				//Get value of Rx 	
	    		String str_rxValue5 = MainApp.myRegisters.getGRValue(rx1);
	    		//Rx value in decimal
	    		int regxvalue5=Decode.binaryToDecimal(str_rxValue5);
	    		
	    		//Get value of RY
	    		String str_ryValue5 = MainApp.myRegisters.getGRValue(ry1);
	    		//Ry value in decimal
	    		int regyvalue5=Decode.binaryToDecimal(str_ryValue5);
	    		//Bitwise AND
	    		int resultAnd= regxvalue5 & regyvalue5;
	    		//Convert into String
	    		String strResult2=Decode.IntegerTo16sBinary(resultAnd);
	    		//store result in Rx
	    		MainApp.myRegisters.writeToGR(rx1,strResult2);
	    		break;
			case 20:// ORR
				//Get value of Rx 	
	    		String str_rxValue1 = MainApp.myRegisters.getGRValue(rx1);
	    		//Rx value in decimal
	    		int regxvalue1=Decode.binaryToDecimal(str_rxValue1);
	    		
	    		//Get value of RY
	    		String str_ryValue1 = MainApp.myRegisters.getGRValue(ry1);
	    		//Ry value in decimal
	    		int regyvalue1=Decode.binaryToDecimal(str_ryValue1);
	    		//Bitwise AND
	    		int resultOR= regxvalue1 | regyvalue1;
	    		//Convert into String
	    		String strResult3=Decode.IntegerTo16sBinary(resultOR);
	    		//store result in Rx
	    		MainApp.myRegisters.writeToGR(rx1,strResult3);
	    		
				
				break;
			case 21:// NOT
				
				String str_rxValue2 = MainApp.myRegisters.getGRValue(rx1);
	    		//Rx value in decimal
	    		int regxvalue2=Decode.binaryToDecimal(str_rxValue2);
	    		//Bitwise NOT
	    		int resultNot= ~regxvalue2;
	    		//Convert into String
	    		String strResult4=Decode.IntegerTo16sBinary(resultNot);
	    		//store result in Rx
	    		MainApp.myRegisters.writeToGR(rx1,strResult4);
	    		
				break;
			case 25:// SRC
				//arithmetic shift
				if (type == 0) {
					String asStr = MainApp.myRegisters.getGRValue(ireg);
					//right
					if (side == 0) {
						char msb = asStr.charAt(0);
						String asSubStr = asStr.substring(0, asStr.length()-count);
						for (int i = 0; i < count; i++) asSubStr = '0'+asSubStr;
						char[] newCharArr = asSubStr.toCharArray();
						newCharArr[0] = msb;
						String newStr = String.valueOf(newCharArr);
						MainApp.myRegisters.writeToGR(ireg ,newStr);
					}
					//left
					else if (side == 1) {
						String asSubStr = asStr.substring(count, asStr.length());
						System.out.println("logic " + asSubStr);
						for (int i = 0; i < count; i++) asSubStr = asSubStr+'0';
						MainApp.myRegisters.writeToGR(ireg ,asSubStr);
					}
					
				}
				//logic shift
				else if (type == 1) {
					String asStr = MainApp.myRegisters.getGRValue(ireg);
					//right
					if (side == 0) {
						String asSubStr = asStr.substring(0, asStr.length()-count);
						for (int i = 0; i < count; i++) asSubStr = '0'+asSubStr;
						MainApp.myRegisters.writeToGR(ireg ,asSubStr);
					}
					//left
					else if (side == 1) {
						String asSubStr = asStr.substring(count, asStr.length());
						//System.out.println("logic " + asSubStr);
						for (int i = 0; i < count; i++) asSubStr = asSubStr+'0';
						MainApp.myRegisters.writeToGR(ireg ,asSubStr);
					}
				}
				
				break;
			case 26: //RRC
				//logic rotate
				if (type == 1) {
					String asStr = MainApp.myRegisters.getGRValue(ireg);
					//right rotate
					if (side == 0) {
						String asSubStr = asStr.substring(0, asStr.length()-count);
						String roSubStr = asStr.substring(asStr.length()-count, asStr.length());
						MainApp.myRegisters.writeToGR(ireg ,roSubStr+asSubStr);
					}
					//left
					else if (side == 1) {
						String asSubStr = asStr.substring(count, asStr.length());
						//System.out.println("logic " + asSubStr);
						String roSubStr = asStr.substring(0, count+1);
						MainApp.myRegisters.writeToGR(ireg ,asSubStr+roSubStr);
					}
				}
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
