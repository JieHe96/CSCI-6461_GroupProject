
public class FPInstruction extends Instruction{
	
    private int opcode;
    private int freg;
    private int index;
    private int instype;
    private int insadd;
    private int ireg;
	// constructor 
    public FPInstruction() {
    	super.value = new Word();
    	opcode = 0;
    	freg = 0;
    	index = 0;
    	instype = 0;
    	insadd = 0;
    	ireg = 0;
    }
	
    public void fetchInstruction() {
		String ins = value.convertToString();
		System.out.println(ins);
		

		String opbinary = ins.substring(0,6);
		opcode = Decode.binaryToDecimal(opbinary);// decimal op
		
		if(opcode == 31)
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

		}
		else 
		{
			String freg_bin = ins.substring(6,8);//F
			String iindex = ins.substring(8,10);//ix
			char temp = ins.charAt(10); //I 
			String itype = new StringBuilder().append("").append(temp).toString();
			String iaddress = ins.substring(11,16);//address
			
			freg = Decode.binaryToDecimal(freg_bin);//decimal f
			index = Decode.binaryToDecimal(iindex);// decimal ix
			instype = Decode.binaryToDecimal(itype);//decimal i
			insadd = Decode.binaryToDecimal(iaddress);//decimal address

		}
		
		MainApp.myRegisters.writeToRegister("IR", ins, 16);
    }
	
    public void execute() {
		
		switch (opcode)
		{
			case 27: 
				//calculate ea
				int sub_ea1 = 0;
	    		if (instype == 0) {
	    			//Direct Addressing
	    			if (index == 0) {
	    				//Get the value stored in insadd
	    				sub_ea1 = insadd;
	    			}
	    			else {
	    				//Get the value stored in [ix] + insadd
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				sub_ea1 = ixValue + insadd;
	    			}
	    		}
	    		else {
	    			//Indirect Addressing
	    			if (index == 0) {
	    				String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
	    				sub_ea1 = Decode.binaryToDecimal(tmp);
	    			}
	    			else {
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				int buffer = ixValue + insadd;
	    				String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
	    				sub_ea1 = Decode.binaryToDecimal(tmp);
	    			}
	    		}
				
	    		//get the fr floating point value
				String fr_str1 = "";
				if (freg == 0) fr_str1 = MainApp.myRegisters.getRegister("FR0", false);
				else fr_str1 = MainApp.myRegisters.getRegister("FR1", false);
				int fr_sign1 = Character.getNumericValue(fr_str1.charAt(0));
				int fr_exsign1 = Character.getNumericValue(fr_str1.charAt(1));
				int fr_exponent1 = Decode.binaryToDecimal(fr_str1.substring(2, 8));
				if (fr_exsign1 == 1) fr_exponent1 *= -1;
				String fr_mantissa1 = fr_str1.substring(8,16);
				//normalize the mantissa
				while (fr_mantissa1.charAt(0) == fr_mantissa1.charAt(1)) {
					fr_mantissa1 = fr_mantissa1.substring(1);
					fr_mantissa1 += "0";
					fr_exponent1 --;
				}
				
				//get the ea floating point value
				String ea_str1 = "";
				ea_str1 = MainApp.myMemory.readFromMemory(sub_ea1).convertToString();
				int ea_sign1 = Character.getNumericValue(fr_str1.charAt(0));
				int ea_exsign1 = Character.getNumericValue(fr_str1.charAt(1));
				int ea_exponent1 = Decode.binaryToDecimal(fr_str1.substring(2, 8));
				if (ea_exsign1 == 1) ea_exponent1 *= -1;
				String ea_mantissa1 = ea_str1.substring(8,16);
				//normalize the mantissa
				while (ea_mantissa1.charAt(0) == ea_mantissa1.charAt(1)) {
					ea_mantissa1 = ea_mantissa1.substring(1);
					ea_mantissa1 += "0";
					ea_exponent1 --;
				}
				
				int res_exponent1 = 0;
				//normalize exponent to the same
				if (fr_exponent1 > ea_exponent1) {
					//calculate the num of bit need to move
					int bit_move = Math.abs(fr_exponent1 - ea_exponent1);
					for (int i = 0; i < bit_move; i++) {
						//remove the last bit
						ea_mantissa1 = ea_mantissa1.substring(0, ea_mantissa1.length()-1);
						//add to the front of the mantissa
						char adding = ea_mantissa1.charAt(0);
						ea_mantissa1 = adding + ea_mantissa1;
					}
					res_exponent1 = fr_exponent1;
				}
				else {
					//calculate the num of bit need to move
					int bit_move = Math.abs(ea_exponent1 - fr_exponent1);
					for (int i = 0; i < bit_move; i++) {
						//remove the last bit
						fr_mantissa1 = fr_mantissa1.substring(0, fr_mantissa1.length()-1);
						//add to the front of the mantissa
						char adding = fr_mantissa1.charAt(0);
						fr_mantissa1 = adding + fr_mantissa1;
					}
					res_exponent1 = ea_exponent1;
				}
				
				int fr_dec1 = Decode.binaryToDecimal(fr_mantissa1);
				int ea_dec1 = Decode.binaryToDecimal(ea_mantissa1);
				if (fr_sign1 == 1) fr_dec1 *= -1;
				if (ea_sign1 == 1) ea_dec1 *= -1;
				int res1 = fr_dec1 + ea_dec1; // adding the two results
				
				char res_sign1 = '0';
				if (res1 < 0) {
					res1 *= -1;
					res_sign1 = '1';
				}
				
				String res_mantissa1 = Integer.toBinaryString(res1);
				if (res_mantissa1.length() < 8) String.format("%8s", res_mantissa1);
				else if (res_mantissa1.length() > 8) res_mantissa1 = res_mantissa1.substring(res_mantissa1.length()-8, res_mantissa1.length());
				String res_exstr1 = "";
				
				if (res_exponent1 < 0) {
					res_exponent1 *= -1;
					res_exstr1 = Integer.toBinaryString(res_exponent1);
					res_exstr1 = '1' + res_exstr1;
				}
				else {
					res_exstr1 = Integer.toBinaryString(res_exponent1);
				}
				
				MainApp.myRegisters.writeToFP(freg, res_sign1, res_exstr1, res_mantissa1);
										
				break;
		
			case 28: 
				
				//calculate ea
				int sub_ea = 0;
	    		if (instype == 0) {
	    			//Direct Addressing
	    			if (index == 0) {
	    				//Get the value stored in insadd
	    				sub_ea = insadd;
	    			}
	    			else {
	    				//Get the value stored in [ix] + insadd
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				sub_ea = ixValue + insadd;
	    			}
	    		}
	    		else {
	    			//Indirect Addressing
	    			if (index == 0) {
	    				String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
	    				sub_ea = Decode.binaryToDecimal(tmp);
	    			}
	    			else {
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				int buffer = ixValue + insadd;
	    				String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
	    				sub_ea = Decode.binaryToDecimal(tmp);
	    			}
	    		}
				
	    		//get the fr floating point value
				String fr_str = "";
				if (freg == 0) fr_str = MainApp.myRegisters.getRegister("FR0", false);
				else fr_str = MainApp.myRegisters.getRegister("FR1", false);
				int fr_sign = Character.getNumericValue(fr_str.charAt(0));
				int fr_exsign = Character.getNumericValue(fr_str.charAt(1));
				int fr_exponent = Decode.binaryToDecimal(fr_str.substring(2, 8));
				if (fr_exsign == 1) fr_exponent *= -1;
				String fr_mantissa = fr_str.substring(8,16);
				//normalize the mantissa
				while (fr_mantissa.charAt(0) == fr_mantissa.charAt(1)) {
					fr_mantissa = fr_mantissa.substring(1);
					fr_mantissa += "0";
					fr_exponent --;
				}
				
				//get the ea floating point value
				String ea_str = "";
				ea_str = MainApp.myMemory.readFromMemory(sub_ea).convertToString();
				int ea_sign = Character.getNumericValue(fr_str.charAt(0));
				int ea_exsign = Character.getNumericValue(fr_str.charAt(1));
				int ea_exponent = Decode.binaryToDecimal(fr_str.substring(2, 8));
				if (ea_exsign == 1) ea_exponent *= -1;
				String ea_mantissa = ea_str.substring(8,16);
				//normalize the mantissa
				while (ea_mantissa.charAt(0) == ea_mantissa.charAt(1)) {
					ea_mantissa = ea_mantissa.substring(1);
					ea_mantissa += "0";
					ea_exponent --;
				}
				
				int res_exponent = 0;
				//normalize exponent to the same
				if (fr_exponent > ea_exponent) {
					//calculate the num of bit need to move
					int bit_move = Math.abs(fr_exponent - ea_exponent);
					for (int i = 0; i < bit_move; i++) {
						//remove the last bit
						ea_mantissa = ea_mantissa.substring(0, ea_mantissa.length()-1);
						//add to the front of the mantissa
						char adding = ea_mantissa.charAt(0);
						ea_mantissa = adding + ea_mantissa;
					}
					res_exponent = fr_exponent;
				}
				else {
					//calculate the num of bit need to move
					int bit_move = Math.abs(ea_exponent - fr_exponent);
					for (int i = 0; i < bit_move; i++) {
						//remove the last bit
						fr_mantissa = fr_mantissa.substring(0, fr_mantissa.length()-1);
						//add to the front of the mantissa
						char adding = fr_mantissa.charAt(0);
						fr_mantissa = adding + fr_mantissa;
					}
					res_exponent = ea_exponent;
				}
				
				int fr_dec = Decode.binaryToDecimal(fr_mantissa);
				int ea_dec = Decode.binaryToDecimal(ea_mantissa);
				if (fr_sign == 1) fr_dec *= -1;
				if (ea_sign == 1) ea_dec *= -1;
				int res = fr_dec - ea_dec;
				
				char res_sign = '0';
				if (res < 0) {
					res *= -1;
					res_sign = '1';
				}
				
				String res_mantissa = Integer.toBinaryString(res);
				if (res_mantissa.length() < 8) String.format("%8s", res_mantissa);
				else if (res_mantissa.length() > 8) res_mantissa = res_mantissa.substring(res_mantissa.length()-8, res_mantissa.length());
				String res_exstr = "";
				
				if (res_exponent < 0) {
					res_exponent *= -1;
					res_exstr = Integer.toBinaryString(res_exponent);
					res_exstr = '1' + res_exstr;
				}
				else {
					res_exstr = Integer.toBinaryString(res_exponent);
				}
				
				MainApp.myRegisters.writeToFP(freg, res_sign, res_exstr, res_mantissa);
				
				
				break;
			
			case 29:
				
				
				if (freg == 0) fr_str = MainApp.myRegisters.getRegister("FR0", false);
				else fr_str = MainApp.myRegisters.getRegister("FR1", false);
				fr_sign = Character.getNumericValue(fr_str.charAt(0));
				fr_exsign = Character.getNumericValue(fr_str.charAt(1));
				fr_exponent = Decode.binaryToDecimal(fr_str.substring(2, 8));
				if (fr_exsign == 1) fr_exponent *= -1;
				fr_mantissa = fr_str.substring(8,16);
				int shift = (fr_mantissa.length() - 1) * -1;
				float decimal_val = Decode.binaryToDecimal(fr_mantissa) * 2 ^ shift;
				decimal_val = decimal_val * (2 ^ fr_exponent);
				
				
				
				break;
			
			
			case 30: break;
			
			case 31: break;

			case 40: {
				int ldfr_ea = 0;
				//Calculate EA
				if (instype == 0) {
					//Direct Addressing
					if (index == 0) {
						//Get the value stored in insadd
						ldfr_ea = insadd;
					}
					else {
						//Get the value stored in [ix] + insadd
						int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
						ldfr_ea = ixValue + insadd;
					}
				}
				else {
					//Indirect Addressing
					if (index == 0) {
						String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
						ldfr_ea = Decode.binaryToDecimal(tmp);
					}
					else {
						int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
						int buffer = ixValue + insadd;
						String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
						ldfr_ea = Decode.binaryToDecimal(tmp);
					}
				}

				//fetching word from memory and converting to string - Getting value at the memory
				String ldfr_ex = MainApp.myMemory.readFromMemory(ldfr_ea).convertToString();
				String ldfr_ma = MainApp.myMemory.readFromMemory(ldfr_ea + 1).convertToString();

				//Store value into FR
				char s = ldfr_ex.charAt(0);
				MainApp.myRegisters.writeToFP(freg, s, ldfr_ex, ldfr_ma);
			}
			break;

			case 41: {
				int stfr_ea = 0;
				//Calculate EA
				if (instype == 0) {
					//Direct addressing
					if (index == 0) {
						stfr_ea = insadd;
					}
					else {
						int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
						stfr_ea = ixValue + insadd;
					}
				}
				else {
					//Indirect addressing
					if (index == 0) {
						String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
						stfr_ea = Decode.binaryToDecimal(tmp);
					}
					else {
						int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
						int buffer = ixValue + insadd;
						String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
						stfr_ea = Decode.binaryToDecimal(tmp);
					}
				}
				//get the fr floating point value
				if (freg == 0) fr_str = MainApp.myRegisters.getRegister("FR0", false);
				else fr_str = MainApp.myRegisters.getRegister("FR1", false);

				MainApp.myMemory.writeToMemory(stfr_ea, fr_str.substring(0, 8));

				MainApp.myMemory.writeToMemory(stfr_ea + 1, fr_str.substring(8, 16));
			}
			break;

		}
    }
    
    
}
