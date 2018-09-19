/**Class that defines instruction objects  */ 

public class Instruction {
	
	private Decode de;
	
    private int opcode;
    private int ireg;
    private int index;
    private int instype;
    private int insadd;
    private int pcAddr;
    private int pcCounter;
    
    public Instruction() {
    	de = new Decode();
    	opcode = 0;
    	ireg = 0;
    	index = 0;
    	instype = 0;
    	insadd = 0;
    	pcAddr = 15; //next free space
    	pcCounter = 15;
    }
    
    public void FetchInstruction(String ins) {

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
        	
        opcode = de.ToDecimal(opbinary);// decimal op
        ireg = de.ToDecimal(iregister);//decimal r
        index = de.ToDecimal(iindex);// decial ix
        instype = de.ToDecimal(itype);//decimal i
        insadd = de.ToDecimal(iaddress);//decimal address
        
        System.out.println(opcode);
        System.out.println(ireg);
        System.out.println(index);
        System.out.println(instype);
        System.out.println(insadd);
        	
    }
    
    public void Execute() {
    	switch (opcode) {
	    	case 1://LDR r, x, address[,I]
	    		if(instype == 0)//immediate addressing
	    		{ 
	    			// move contents of address to the register 
	    			// Word r = MainApp.myMemory.GetRegisterValue(ireg);// 
	    			Word ix = MainApp.myMemory.GetIXValue(index);
	    			
	    			
	    		}
	    		else if (instype == 1) // indirect addressing 
	    			
	    		break;
	    	case 2: // STR r,x,address 
	    		//store the contents of the register to the memory of the effective address
	    		break;
	    	case 3: // LDA r ,x,address 
	    		//Load the register with the address (not the content) 
	    		break;
	    	case 33:// LDX x,address 
	    		//Load index register with contents of the  memory location
	    		break;
	    	case 34://STX x,address 
	    		//Store the index Register value into the Memory Location
	    		break;
	    	case 8:// JZ r,x,address :- Jump if Zero
	    			// if c(r)=0 -> PC=EA else PC=PC+1
	    		break;
	    	case 9: //JNE r,x,address : Jump if not equal
	    		// if c(r)!=0 -> PC=EA else PC=PC+1
	    		break;
	    	case 10: // JCC cc,x,address : Jump if Condition Code 
	    		// if cc bit=1 , PC=EA else PC=PC+1
	    		break;
    	}
    	
    }

    public void AddToPC(String ins) {
    	Word tmp = new Word();
    	for (int i = 0; i < 16; i++) {
    		if (ins.charAt(i) == '1') tmp.set(i, true);
    		else tmp.set(i, false);
    	}
    	MainApp.myMemory.GetMemo().set(pcAddr, tmp);
    	pcAddr++;
    }
    
    public void IncreasePC() {
    	pcCounter++;
    }
    
    public int GetPCAddr() {
    	return pcAddr;
    }
    
    public int GetCounter() {
    	return pcCounter;
    }
    
    public boolean ResetPC() {
    	if (pcCounter == pcAddr) {
    		pcAddr = 15;
    		pcCounter = 15;
    		return true;
    	}
    	return false;
    }

    // convert input into object values using switch case 

}