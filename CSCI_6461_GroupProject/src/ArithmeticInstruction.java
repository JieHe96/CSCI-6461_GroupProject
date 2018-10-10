
public class ArithmeticInstruction extends Instruction{

	
	private int opcode;
	private int ireg;
	private int index;
	private int instype;
	private int insadd;
	//constructor
	public ArithmeticInstruction() {
		super.value = new Word();
		opcode = 0;
		ireg = 0;
		index = 0;
		instype = 0;
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
				
			
			 //LONG METHOD - MAY OR O MAYNOT USE 
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
				// Add contents of register to contents of art_ea and store in register
				 result = addBinary( tmp , amr_mValue ); // ?? EERROR HEREEEE
						 
								 
				 //store result in register
				 MainApp.myRegisters.writeToGR(ireg,result);

			 	
			break; // end of case 4 
		
		case 5: // SMR r, x, address[,I] :- r<- c(r) – c(EA)
			
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
	    		int result=regvalue-effvalue;
	    		//Convert into String
	    		String strResult=Decode.IntegerTo16sBinary(result);
				//store result in register
	    		MainApp.myRegisters.writeToGR(ireg,result);

		 	
			
			break;
		case 6:// AIR
			break;
		case 7: // SIR 
			break;
		case 16: // MLT
			
			
			break;
		case 17: //DVD
			break;
			case 49: // IN 
			break;
		case 50://OUT
			break;
			
	/*	case 51://CHK
			break;	
	*/	
		}// switch case closed
		
	}
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
	
	
}
