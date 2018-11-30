
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
//				//normalize the mantissa
//				while (fr_mantissa1.charAt(0) == fr_mantissa1.charAt(1)) {
//					fr_mantissa1 = fr_mantissa1.substring(1);
//					fr_mantissa1 += "0";
//					fr_exponent1 --;
//				}
				
				//get the ea floating point value
				String ea_str1 = "";
				ea_str1 = MainApp.myMemory.readFromMemory(sub_ea1).convertToString();
				int ea_sign1 = Character.getNumericValue(ea_str1.charAt(0));
				int ea_exsign1 = Character.getNumericValue(ea_str1.charAt(1));
				int ea_exponent1 = Decode.binaryToDecimal(ea_str1.substring(2, 8));
				if (ea_exsign1 == 1) ea_exponent1 *= -1;
				String ea_mantissa1 = ea_str1.substring(8,16);
//				//normalize the mantissa
//				while (ea_mantissa1.charAt(0) == ea_mantissa1.charAt(1)) {
//					ea_mantissa1 = ea_mantissa1.substring(1);
//					ea_mantissa1 += "0";
//					ea_exponent1 --;
//				}
				
				int res_exponent1 = 0;
				System.out.println("fr_exp: " + fr_exponent1);
				System.out.println("ea_exp: " + ea_exponent1);
				//normalize exponent to the same
				if (fr_exponent1 > ea_exponent1) {
					//calculate the num of bit need to move
					int bit_move = Math.abs(fr_exponent1 - ea_exponent1);
					for (int i = 0; i < bit_move; i++) {
						//remove the last bit
						ea_mantissa1 = ea_mantissa1.substring(0, ea_mantissa1.length()-1);
						//add to the front of the mantissa
						//char adding = ea_mantissa1.charAt(0);
						ea_mantissa1 = '0' + ea_mantissa1;
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
						//char adding = fr_mantissa1.charAt(0);
						fr_mantissa1 = '0' + fr_mantissa1;
					}
					res_exponent1 = ea_exponent1;
				}
				System.out.println("res_exp: " + res_exponent1);
				
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
				if (res_mantissa1.length() < 8)res_mantissa1 = String.format("%8s", res_mantissa1).replace(" ", "0");
				else if (res_mantissa1.length() > 8) res_mantissa1 = res_mantissa1.substring(res_mantissa1.length()-8, res_mantissa1.length());
				String res_exstr1 = "";
				
				if (res_exponent1 < 0) {
					res_exponent1 *= -1;
					res_exstr1 = Integer.toBinaryString(res_exponent1);
					System.out.println(res_exstr1);
					System.out.println(res_exstr1.length());
					res_exstr1 = String.format("%6s", res_exstr1).replace(" ", "0");
					res_exstr1 = '1' + res_exstr1;
				}
				else {
					res_exstr1 = Integer.toBinaryString(res_exponent1);
					res_exstr1 = String.format("%7s", res_exstr1).replace(" ", "0");
				}
				System.out.println(freg +"   " +res_sign1 +"   " + res_exstr1 +"   " + res_mantissa1);
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
//				//normalize the mantissa
//				while (fr_mantissa.charAt(0) == fr_mantissa.charAt(1)) {
//					fr_mantissa = fr_mantissa.substring(1);
//					fr_mantissa += "0";
//					fr_exponent --;
//				}
				
				//get the ea floating point value
				String ea_str = "";
				ea_str = MainApp.myMemory.readFromMemory(sub_ea).convertToString();
				int ea_sign = Character.getNumericValue(ea_str.charAt(0));
				int ea_exsign = Character.getNumericValue(ea_str.charAt(1));
				int ea_exponent = Decode.binaryToDecimal(ea_str.substring(2, 8));
				if (ea_exsign == 1) ea_exponent *= -1;
				String ea_mantissa = ea_str.substring(8,16);
//				//normalize the mantissa
//				while (ea_mantissa.charAt(0) == ea_mantissa.charAt(1)) {
//					ea_mantissa = ea_mantissa.substring(1);
//					ea_mantissa += "0";
//					ea_exponent --;
//				}
				
				int res_exponent = 0;
				//normalize exponent to the same
				if (fr_exponent > ea_exponent) {
					//calculate the num of bit need to move
					int bit_move = Math.abs(fr_exponent - ea_exponent);
					for (int i = 0; i < bit_move; i++) {
						//remove the last bit
						ea_mantissa = ea_mantissa.substring(0, ea_mantissa.length()-1);
						//add to the front of the mantissa
						//char adding = ea_mantissa.charAt(0);
						ea_mantissa = '0' + ea_mantissa;
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
						//char adding = fr_mantissa.charAt(0);
						fr_mantissa = '0' + fr_mantissa;
					}
					res_exponent = ea_exponent;
				}
				System.out.println("res_exp: " + res_exponent);
				
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
				System.out.println("res_mantissa (before)= " + res_mantissa);
				if (res_mantissa.length() < 8) res_mantissa = String.format("%8s", res_mantissa).replace(" ", "0");
				else if (res_mantissa.length() > 8) res_mantissa = res_mantissa.substring(res_mantissa.length()-8, res_mantissa.length());
				String res_exstr = "";
				System.out.println("res_mantissa = " + res_mantissa);
				if (res_exponent < 0) {
					res_exponent *= -1;
					System.out.println(res_exstr);
					System.out.println(res_exstr.length());
					res_exstr = Integer.toBinaryString(res_exponent);
					res_exstr = String.format("%6s", res_exstr).replace(" ", "0");
					res_exstr = '1' + res_exstr;
				}
				else {
					res_exstr = Integer.toBinaryString(res_exponent);
					res_exstr = String.format("%7s", res_exstr).replace(" ", "0");
				}
				System.out.println(freg +"   " +res_sign +"   " + res_exstr +"   " + res_mantissa);
				
				MainApp.myRegisters.writeToFP(freg, res_sign, res_exstr, res_mantissa);
				
				
				break;
			
			case 29:
				
				
				if (freg == 0) fr_str = MainApp.myRegisters.getRegister("FR0", false);
				else fr_str = MainApp.myRegisters.getRegister("FR1", false);
				fr_sign = Character.getNumericValue(fr_str.charAt(0));
				
				if (fr_sign == '1') break;
				
				fr_exsign = Character.getNumericValue(fr_str.charAt(1));
				fr_exponent = Decode.binaryToDecimal(fr_str.substring(2, 8));
				if (fr_exsign == 1) fr_exponent *= -1;
				fr_mantissa = fr_str.substring(8,16);
				int shift = (fr_mantissa.length() - 1);
				System.out.println(fr_mantissa);
				//System.out.println(2 ^ shift);
				float decimal_val = Decode.binaryToDecimal(fr_mantissa);
				for (int i = 0; i < shift; i++) {
					decimal_val = decimal_val / 2;
				}
				System.out.println(decimal_val);
				
				if (fr_exponent < 0) {
					for (int i = 0; i < -fr_exponent; i++) {
						decimal_val = decimal_val / 2;
					}
				}
				else {
					for (int i = 0; i < fr_exponent; i++) {
						decimal_val = decimal_val * 2;
					}
				}
				
				System.out.println(decimal_val);
				
				if (decimal_val != (float)((int)decimal_val)) break;
				
				int vadd_ea = 0;
	    		if (instype == 0) {
	    			//Direct Addressing
	    			if (index == 0) {
	    				//Get the value stored in insadd
	    				vadd_ea = insadd;
	    			}
	    			else {
	    				//Get the value stored in [ix] + insadd
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				vadd_ea = ixValue + insadd;
	    			}
	    		}
	    		else {
	    			//Indirect Addressing
	    			if (index == 0) {
	    				String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
	    				vadd_ea = Decode.binaryToDecimal(tmp);
	    			}
	    			else {
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				int buffer = ixValue + insadd;
	    				String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
	    				vadd_ea = Decode.binaryToDecimal(tmp);
	    			}
	    		}
	    		
	    		String v1_start = MainApp.myMemory.readFromMemory(vadd_ea).convertToString();
	    		String v2_start = MainApp.myMemory.readFromMemory(vadd_ea+1).convertToString();
	    		
	    		int v1_addr = Decode.binaryToDecimal(v1_start);
	    		int v2_addr = Decode.binaryToDecimal(v2_start);
	    		decimal_val = (int) decimal_val;
	    		
	    		for (int i = 0; i < decimal_val; i++) {
	    			String v1_str = MainApp.myMemory.readFromMemory(v1_addr+i).convertToString();
	    			String v2_str = MainApp.myMemory.readFromMemory(v2_addr+i).convertToString();
	    			
	    			int result = Decode.binaryToDecimal(v1_str) + Decode.binaryToDecimal(v2_str);
	    			String res_str = Integer.toString(result, 2);
	    			
	    			if (res_str.length() > 16) {
	    				res_str = res_str.substring(0, 16);
	    			}
	    			else {
	    				res_str = String.format("%16s", res_str).replace(" ", "0");
	    			}
	    			System.out.println(res_str);
	    			MainApp.myMemory.writeToMemory(v1_addr+i, res_str);
	    		}
				
				break;
			
			//change to 42
			case 42: 
				
				if (freg == 0) fr_str = MainApp.myRegisters.getRegister("FR0", false);
				else fr_str = MainApp.myRegisters.getRegister("FR1", false);
				fr_sign = Character.getNumericValue(fr_str.charAt(0));
				
				if (fr_sign == '1') break;
				
				fr_exsign = Character.getNumericValue(fr_str.charAt(1));
				fr_exponent = Decode.binaryToDecimal(fr_str.substring(2, 8));
				if (fr_exsign == 1) fr_exponent *= -1;
				fr_mantissa = fr_str.substring(8,16);
				shift = (fr_mantissa.length() - 1);
				System.out.println(fr_mantissa);
				//System.out.println(2 ^ shift);
				decimal_val = Decode.binaryToDecimal(fr_mantissa);
				for (int i = 0; i < shift; i++) {
					decimal_val = decimal_val / 2;
				}
				System.out.println(decimal_val);
				
				if (fr_exponent < 0) {
					for (int i = 0; i < -fr_exponent; i++) {
						decimal_val = decimal_val / 2;
					}
				}
				else {
					for (int i = 0; i < fr_exponent; i++) {
						decimal_val = decimal_val * 2;
					}
				}
				
				System.out.println(decimal_val);
				
				if (decimal_val != (float)((int)decimal_val)) break;
				
				int vsub_ea = 0;
	    		if (instype == 0) {
	    			//Direct Addressing
	    			if (index == 0) {
	    				//Get the value stored in insadd
	    				vsub_ea = insadd;
	    			}
	    			else {
	    				//Get the value stored in [ix] + insadd
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				vsub_ea = ixValue + insadd;
	    			}
	    		}
	    		else {
	    			//Indirect Addressing
	    			if (index == 0) {
	    				String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
	    				vsub_ea = Decode.binaryToDecimal(tmp);
	    			}
	    			else {
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				int buffer = ixValue + insadd;
	    				String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
	    				vsub_ea = Decode.binaryToDecimal(tmp);
	    			}
	    		}
	    		
	    		v1_start = MainApp.myMemory.readFromMemory(vsub_ea).convertToString();
	    		v2_start = MainApp.myMemory.readFromMemory(vsub_ea+1).convertToString();
	    		
	    		v1_addr = Decode.binaryToDecimal(v1_start);
	    		v2_addr = Decode.binaryToDecimal(v2_start);
	    		decimal_val = (int) decimal_val;
	    		
	    		for (int i = 0; i < decimal_val; i++) {
	    			String v1_str = MainApp.myMemory.readFromMemory(v1_addr+i).convertToString();
	    			String v2_str = MainApp.myMemory.readFromMemory(v2_addr+i).convertToString();
	    			
	    			int result = Decode.binaryToDecimal(v1_str) - Decode.binaryToDecimal(v2_str);
	    			String res_str = Integer.toString(result, 2);
	    			
	    			if (res_str.length() > 16) {
	    				res_str = res_str.substring(0, 16);
	    			}
	    			else {
	    				res_str = String.format("%16s", res_str).replace(" ", "0");
	    			}
	    			System.out.println(res_str);
	    			MainApp.myMemory.writeToMemory(v1_addr+i, res_str);
	    		}
				
				
				break;
			
			case 31: 
				
				int convrt_ea = 0;
	    		if (instype == 0) {
	    			//Direct Addressing
	    			if (index == 0) {
	    				//Get the value stored in insadd
	    				convrt_ea = insadd;
	    			}
	    			else {
	    				//Get the value stored in [ix] + insadd
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				convrt_ea = ixValue + insadd;
	    			}
	    		}
	    		else {
	    			//Indirect Addressing
	    			if (index == 0) {
	    				String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
	    				convrt_ea = Decode.binaryToDecimal(tmp);
	    			}
	    			else {
	    				int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
	    				int buffer = ixValue + insadd;
	    				String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
	    				convrt_ea = Decode.binaryToDecimal(tmp);
	    			}
	    		}
				
				String r_str = MainApp.myRegisters.getGRValue(ireg);
				int f_val = Decode.binaryToDecimal(r_str);
				if (f_val == 0) {
					//floating to fix
					String num_str = MainApp.myMemory.readFromMemory(convrt_ea).convertToString();
					
					int num_sign = Character.getNumericValue(num_str.charAt(0));
					int num_exsign = Character.getNumericValue(num_str.charAt(1));
					int num_exponent = Decode.binaryToDecimal(num_str.substring(2, 8));
					
					String num_mantissa = num_str.substring(8,16);
					
					String res_fix = "";
					
					if (num_exsign == 1) {
						res_fix = "0";
					}
					else {
						if (num_exponent > 7) {
							for (int i = 0; i < num_exponent - 7; i++) {
								num_mantissa += "0";
							}
							res_fix = num_mantissa;
						}
						else {
							num_mantissa = num_mantissa.substring(0, num_exponent+1);
							res_fix = num_mantissa;
						}
					}
					res_fix = String.format("%16s", res_fix).replace(" ", "0");
					System.out.println("-----" + res_fix);
					if (num_sign == 1) {
						res_fix = '1' + res_fix.substring(1, res_fix.length());
					}
					System.out.println(res_fix);
					MainApp.myRegisters.writeToGR(ireg, res_fix);
				}
				else {
					//fix to floating point
					String num_str = MainApp.myMemory.readFromMemory(convrt_ea).convertToString();
					
					char sign = '0';
					int exponent = 0;
					while(num_str.charAt(0) == '0') {
						num_str = num_str.substring(1);
					}
					System.out.println("----"+num_str);
					exponent = num_str.length()-1;
					
					while(num_str.length() < 8) {
						num_str+='0';
					}
					
					String exp_str = Integer.toBinaryString(exponent);
					exp_str = String.format("%7s", exp_str).replace(" ", "0");
					
					System.out.println("===" + exp_str);
					System.out.println(num_str);
					
					MainApp.myRegisters.writeToFP(0, sign, exp_str, num_str.substring(0, 8));
					
					
				}
				
				break;

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
				String ldfr_str = MainApp.myMemory.readFromMemory(ldfr_ea).convertToString();
				//creating 16 bit string of EA
				String ldfr_eaStr = Decode.IntegerTo16sBinary(ldfr_ea);
				//fetching word from memory and converting to string - Getting value at the memory

				if (MainApp.myMemory.readFromMemory(ldfr_ea).checkFlag()) {
					System.out.println("=================");
					MainApp.myRegisters.setCharMap(ireg, 1);
				}
				//store address into MAR
				MainApp.myRegisters.writeToRegister("MAR", ldfr_eaStr, 16);
				//Store value into MBR
				MainApp.myRegisters.writeToRegister("MBR", ldfr_str, 16);

				//Store value into FR
				char s = ldfr_str.charAt(0);
				MainApp.myRegisters.writeToFP(freg, s, ldfr_str.substring(1,8), ldfr_str.substring(8,15));
				
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

				MainApp.myMemory.writeToMemory(stfr_ea, fr_str);

				//Set MAR = EA, MBR = [RR], then write to memory
				String stfr_eaStr = Decode.IntegerTo16sBinary(stfr_ea);
				String stfr_rValue = fr_str;
				MainApp.myRegisters.writeToRegister("MAR", stfr_eaStr, 16);
				MainApp.myRegisters.writeToRegister("MBR", stfr_rValue, 16);
			}
			break;

		}
    }
    
    
}
