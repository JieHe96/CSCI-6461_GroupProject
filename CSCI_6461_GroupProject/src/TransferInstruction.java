import com.sun.tools.javac.Main;

public class TransferInstruction extends Instruction{

	private int opcode;
	private int ireg;
	private int index;
	private int instype;
	private int insadd;
    boolean cc;


	//constructor
	public TransferInstruction() {
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

	}


	//get condition code
	public void getCC() {
        String ctemp = value.convertToString();
        String icc = ctemp.substring(6, 8);//condition code
/*
        switch (icc){
            case "00":
                // OVERFLOW
                cc = ;
                break;
            case "01":
                // UNDERFLOW
                cc = ;
                break;
            case "10":
                // DIVZERO
                cc = ;
                break;
            case "11":
                // EQUALORNOT
                cc = ;
                break;

        }
        */
	}


        //execute the instruction by switch case
        public void execute() {
            switch (opcode) {
                case 8://JZ If c(r) = 0, then PC <- EA
                    if (Integer.parseInt(MainApp.myRegisters.getGRValue(ireg)) == 0) {
                        int tra_ea = 0;
                        //Calculate EA
                        if (instype == 0) {
                            //Direct addressing
                            if (index == 0) {
                                tra_ea = insadd;
                            } else {
                                int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
                                tra_ea = ixValue + insadd;
                            }
                        } else {
                            //Indirect addressing
                            if (index == 0) {
                                String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
                                tra_ea = Decode.binaryToDecimal(tmp);
                            } else {
                                int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
                                int buffer = ixValue + insadd;
                                String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
                                tra_ea = Decode.binaryToDecimal(tmp);
                            }
                        }
                        //PC <-- EA
                        MainApp.myRegisters.writeToPC(String.valueOf(tra_ea));
                    }
                    break;


                case 9://JNE If c(r) != 0, then PC <- EA
                    if (Integer.parseInt(MainApp.myRegisters.getGRValue(ireg)) != 0) {
                        int tra_ea = 0;
                        //Calculate EA
                        if (instype == 0) {
                            //Direct addressing
                            if (index == 0) {
                                tra_ea = insadd;
                            } else {
                                int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
                                tra_ea = ixValue + insadd;
                            }
                        } else {
                            //Indirect addressing
                            if (index == 0) {
                                String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
                                tra_ea = Decode.binaryToDecimal(tmp);
                            } else {
                                int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
                                int buffer = ixValue + insadd;
                                String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
                                tra_ea = Decode.binaryToDecimal(tmp);
                            }
                        }
                        //PC <-- EA
                        MainApp.myRegisters.writeToPC(String.valueOf(tra_ea));
                    }
                    break;


                case 10://JCC cc, x, address[,I]
                    getCC();

                    if (cc) {
                        //PC <-EA
                        {
                            int tra_ea = 0;
                            //Calculate EA
                            if (instype == 0) {
                                //Direct addressing
                                if (index == 0) {
                                    tra_ea = insadd;
                                } else {
                                    int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
                                    tra_ea = ixValue + insadd;
                                }
                            } else {
                                //Indirect addressing
                                if (index == 0) {
                                    String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
                                    tra_ea = Decode.binaryToDecimal(tmp);
                                } else {
                                    int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
                                    int buffer = ixValue + insadd;
                                    String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
                                    tra_ea = Decode.binaryToDecimal(tmp);
                                }
                            }
                            //PC <-- EA
                            MainApp.myRegisters.writeToPC(String.valueOf(tra_ea));
                        }
                    }
                    break;


                case 11://JMA Unconditional Jump To Address
                {
                    int tra_ea = 0;
                    //Calculate EA
                    if (instype == 0) {
                        //Direct addressing
                        if (index == 0) {
                            tra_ea = insadd;
                        } else {
                            int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
                            tra_ea = ixValue + insadd;
                        }
                    } else {
                        //Indirect addressing
                        if (index == 0) {
                            String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
                            tra_ea = Decode.binaryToDecimal(tmp);
                        } else {
                            int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
                            int buffer = ixValue + insadd;
                            String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
                            tra_ea = Decode.binaryToDecimal(tmp);
                        }
                    }
                    //PC <-- EA
                    MainApp.myRegisters.writeToPC(String.valueOf(tra_ea));
                }
                break;


                case 12://JSR x, address[,I]  R3 <- PC+1     PC<-EA
                    //R3 <- PC+1
                    MainApp.myRegisters.writeToGR(3, MainApp.myRegisters.getPCValue() + 1);
                {
                    int tra_ea = 0;
                    //PC <- EA
                    //Calculate EA
                    if (instype == 0) {
                        //Direct addressing
                        if (index == 0) {
                            tra_ea = insadd;
                        } else {
                            int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
                            tra_ea = ixValue + insadd;
                        }
                    } else {
                        //Indirect addressing
                        if (index == 0) {
                            String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
                            tra_ea = Decode.binaryToDecimal(tmp);
                        } else {
                            int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
                            int buffer = ixValue + insadd;
                            String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
                            tra_ea = Decode.binaryToDecimal(tmp);
                        }
                    }
                    //PC <-- EA
                    MainApp.myRegisters.writeToPC(String.valueOf(tra_ea));
                }
                break;


                case 13://RFS Immed
                    //R0 <- Immed;
                    MainApp.myRegisters.writeToGR(0, MainApp.myRegisters.getRegister("IR", true).substring(11, 16));

                    //PC <- c(R3)
                    MainApp.myRegisters.writeToPC(MainApp.myRegisters.getGRValue(3));
                    break;


                case 14://SOB r, x, address[,I]
                    //r <- c(r)-1
                    MainApp.myRegisters.writeToGR(ireg, MainApp.myRegisters.getGRValue(ireg - 1));


                    //if c(r)>0, PC <- EA
                    if (Integer.parseInt(MainApp.myRegisters.getGRValue(ireg)) > 0) {
                        int tra_ea = 0;
                        //Calculate EA
                        if (instype == 0) {
                            //Direct addressing
                            if (index == 0) {
                                tra_ea = insadd;
                            } else {
                                int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
                                tra_ea = ixValue + insadd;
                            }
                        } else {
                            //Indirect addressing
                            if (index == 0) {
                                String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
                                tra_ea = Decode.binaryToDecimal(tmp);
                            } else {
                                int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
                                int buffer = ixValue + insadd;
                                String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
                                tra_ea = Decode.binaryToDecimal(tmp);
                            }
                        }
                        //PC <-- EA
                        MainApp.myRegisters.writeToPC(String.valueOf(tra_ea));
                    }
                    break;


                case 15://JGE r,x, address[,I]
                    //if c(r) >= 0, PC <- EA
                    if (Integer.parseInt(MainApp.myRegisters.getGRValue(ireg)) >= 0) {
                        int tra_ea = 0;
                        //Calculate EA
                        if (instype == 0) {
                            //Direct addressing
                            if (index == 0) {
                                tra_ea = insadd;
                            } else {
                                int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
                                tra_ea = ixValue + insadd;
                            }
                        } else {
                            //Indirect addressing
                            if (index == 0) {
                                String tmp = MainApp.myMemory.readFromMemory(insadd).convertToString();
                                tra_ea = Decode.binaryToDecimal(tmp);
                            } else {
                                int ixValue = Integer.parseInt(MainApp.myRegisters.getIXValue(index));
                                int buffer = ixValue + insadd;
                                String tmp = MainApp.myMemory.readFromMemory(buffer).convertToString();
                                tra_ea = Decode.binaryToDecimal(tmp);
                            }
                        }
                        //PC <-- EA
                        MainApp.myRegisters.writeToPC(String.valueOf(tra_ea));
                    }
                    break;


            }
        }


}
