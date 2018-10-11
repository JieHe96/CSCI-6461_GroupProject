
public class ArithmeticInstruction extends Instruction{

	
	private int opcode;
	private int ireg;
	private int index;
	private int instype;
	private int insadd;
	private int rx;
	private int ry;
	
	
	
	//constructor
	public ArithmeticInstruction() {
		super.value = new Word();
		opcode = 0;
		ireg = 0;
		index = 0;
		instype = 0;
		rx = 0;
		ry = 0;
		insadd = 0;
		
    }
	
	//fetch the instruction and decode
	public void fetchInstruction() {
		String ins = value.convertToString();
		System.out.println(ins);
		
// FOR 4,5,6,7 - THIS IS FETCH INSTRUCTION 
		
		// Instruction register
		// insert fetch instruction from input screen and assign to variable ir
		//fetching various parts of the instructions
		//operand part of the instruction

		String opbinary = ins.substring(0,6);
		opcode = Decode.binaryToDecimal(opbinary);// decimal op
		
		if( opcode == 4 ||opcode ==  5 ||opcode ==  6 ||opcode ==  7)
		{
		String iregister = ins.substring(6,8);//r
		String iindex = ins.substring(8,10);//ix
		char temp = ins.charAt(10);
		String itype = new StringBuilder().append("").append(temp).toString();
		String iaddress = ins.substring(11,16);//address
		
		ireg = Decode.binaryToDecimal(iregister);//decimal r
		index = Decode.binaryToDecimal(iindex);// decial ix
		instype = Decode.binaryToDecimal(itype);//decimal i
		insadd = Decode.binaryToDecimal(iaddress);//decimal address

		System.out.println(opcode);
		System.out.println(ireg);
		System.out.println(index);
		System.out.println(instype);
		System.out.println(insadd);
		
		}
		else if (opcode == 16 ||opcode ==  17 )
		{
			String str_rx = ins.substring(6, 8); // Rx
			String str_ry = ins.substring(8, 10); // Rx
			
			rx = Decode.binaryToDecimal(str_rx);//decimal RX
			ry = Decode.binaryToDecimal(str_ry);// decial RY
			
			System.out.println(opcode);
			System.out.println(rx);
			System.out.println(ry);
			
		}
			
		//Printing to IR 
		MainApp.myRegisters.writeToRegister("IR", ins, 16);
	}

	/*
	//get the effective address
	public String getEA() {
		int tra_ea = 0;
		//Calculate EA
		if (instype == 0) {
			//Direct addressing
			if (index == 0) {
				tra_ea = insadd;
			}
			else {
				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
				tra_ea = ixValue + insadd;
			}
		}
		else {
			//Indirect addressing
			if (index == 0) {
				String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
				tra_ea = Decode.binaryToDecimal(tmp);
			}
			else {
				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
				int buffer = ixValue + insadd;
				String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
				tra_ea = Decode.binaryToDecimal(tmp);
			}
		}

		//creating 16 bit string of EA
		String tra_eaStr = Decode.IntegerTo16sBinary(tra_ea);

		return tra_eaStr;
	}
	*/

	
		
	//execute the instruction by switch case
	public void execute() {
		
		switch (opcode)
		{
		 //4,5,6,7,16,17,18,19,20,21,25,26,49,50,51
		
		case 4: //AMR r, x, address[,I] :- r <- c(r) + c(EA)
			 
			  String tmp=null,result,amr_mValue;
			  
			int arth_ea = 0;
			//Calculate EA
			if (instype == 0) {
				//Direct addressing
				if (index == 0) {
					arth_ea = insadd;
				}
				else {
					int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
					arth_ea = ixValue + insadd;
				}
			}
			else {
				//Indirect addressing
				if (index == 0) {
					tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
					arth_ea = Decode.binaryToDecimal(tmp);
				}
				else {
					int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
					int buffer = ixValue + insadd;
					tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
					arth_ea = Decode.binaryToDecimal(tmp);
				}
			}
				amr_mValue = MainApp.myMemory.readFromMemory(arth_ea).convertToString();
			// Subtract contents of register to contents of art_ea and store in register
				//Get value of GR 	
	    		String str_rValue = MainApp.myRegisters.getGRValue(ireg);
	    		//GP value in decimal
	    		int regvalue=Decode.binaryToDecimal(str_rValue);
	    		//Effective address value in decimal
	    		int effvalue=Decode.binaryToDecimal(amr_mValue);
	    		//Subtraction in Decimal
	    		int result1=regvalue+effvalue;
	    		//Convert into String
	    		String strResult=Decode.IntegerTo16sBinary(result1);
				//store result in register
	    		MainApp.myRegisters.writeToGR(ireg,strResult);
	 					
			
			
			
/*			 //LONG METHOD - MAY OR O MAYNOT USE
			 
			
			int arth_ea = 0;
				//Calculate EA
				if (instype == 0) {
					//Direct addressing
					if (index == 0) {
						arth_ea = insadd;
					}
					else {
						int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
						arth_ea = ixValue + insadd;
					}
				}
				else {
					//Indirect addressing
					if (index == 0) {
						tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
						arth_ea = Decode.binaryToDecimal(tmp);
					}
					else {
						int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
						int buffer = ixValue + insadd;
						tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
						arth_ea = Decode.binaryToDecimal(tmp);
					}
				}
					amr_mValue = MainApp.myMemory.readFromMemory(arth_ea).convertToString();
				// Add contents of register to contents of art_ea and store in register
				 result = addBinary( tmp , amr_mValue ); //
						 
								 
				 //store result in register
				 MainApp.myRegisters.writeToGR(ireg,result);

			*/	 	
			break; // end of case 4 
		
		
		case 5: // SMR r, x, address[,I] :- r<- c(r) – c(EA)
			
			int arth_ea1 = 0;
			//Calculate EA
			if (instype == 0) {
				//Direct addressing
				if (index == 0) {
					arth_ea1 = insadd;
				}
				else {
					int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
					arth_ea1 = ixValue + insadd;
				}
			}
			else {
				//Indirect addressing
				if (index == 0) {
					tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
					arth_ea1 = Decode.binaryToDecimal(tmp);
				}
				else {
					int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
					int buffer = ixValue + insadd;
					tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
					arth_ea1 = Decode.binaryToDecimal(tmp);
				}
			}
				amr_mValue = MainApp.myMemory.readFromMemory(arth_ea1).convertToString();
			// Subtract contents of register to contents of art_ea and store in register
				//Get value of GR 	
	    		String str_rValue1 = MainApp.myRegisters.getGRValue(ireg);
	    		//GP value in decimal
	    		int regvalue1=Decode.binaryToDecimal(str_rValue1);
	    		//Effective address value in decimal
	    		int effvalue1=Decode.binaryToDecimal(amr_mValue);
	    		//Subtraction in Decimal
	    		int result11=regvalue1-effvalue1;
	    		//Convert into String
	    		String strResult1=Decode.IntegerTo16sBinary(result11);
				//store result in register
	    		MainApp.myRegisters.writeToGR(ireg,strResult1);
	 				
			break;
		case 6:// AIR  ( r <- c(r) + Immed )
			
			//Get value of GR 	
    		String str_rValue11 = MainApp.myRegisters.getGRValue(ireg);
    		//GP value in decimal
    		int regvalue11=Decode.binaryToDecimal(str_rValue11);
    		// Add value of Register with Immediate value=insadd
			int result2 = regvalue11 + insadd;
			//Convert into String
    		String strResult11=Decode.IntegerTo16sBinary(result2);
			//store result in register
    		MainApp.myRegisters.writeToGR(ireg,strResult11);
					
			break;
		case 7: // SIR ( r <- c(r) - Immed )
			//Get value of GR 	
    		String str_rValue3 = MainApp.myRegisters.getGRValue(ireg);
    		//GP value in decimal
    		int regvalue2=Decode.binaryToDecimal(str_rValue3);
    		// Add value of Register with Immediate value=insadd
			int result3 = regvalue2 - insadd;
			//Convert into String
    		String strResult2=Decode.IntegerTo16sBinary(result3);
			//store result in register
    		MainApp.myRegisters.writeToGR(ireg,strResult2);
			break;
		case 16: // MLT
			
			
			break;
		case 17: //DVD
			break;
		

		}// switch case closed
		
	}
	
	/*
	// method for addition of two binary strings 	 
	public String addBinary(String s1, String s2) {
    StringBuilder sb = new StringBuilder();
    int i = s1.length() - 1, j = s2.length() -1, carry = 0;
    while (i >= 0 || j >= 0) {
        int sum = carry;
        if (j >= 0) sum += s2.charAt(j--) - '0';
        if (i >= 0) sum += s1.charAt(i--) - '0';
        sb.append(sum % 2);
        carry = sum / 2;
    }
    if (carry != 0) sb.append(carry);
    return sb.reverse().toString();
}
	*/
	
}
