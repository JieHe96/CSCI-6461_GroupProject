/**Class that defines instruction objects  */ 

public class Instruction 
{ 
	Decode de = new Decode();
	
	
	
	   //instruction fetch

    public int iregister, iindex, itype, iaddress, hh;

    public void instruction fetch(String IR) throws Exception 
    	{

        // Instruction register 
        // insert fetch instruction from input screen and assingn to variable ir 
		
    //fetching various parts of the instructions 
     
        //operand part of the instruction
        	String opbinary =IR.substring(0,5);
           	String iregister =IR.substring(6,7);//r
         	String iindex =IR.substring(8,9);//ix
        	String itype =IR.substring(10);//i
        	String iaddress =IR.substring(11,15);//ad
        	
        	int opcode=de.ToDecimal(opbinary);// decimal op
        	int ireg=de.decimal(iregister);//decimal r
        	int index=de.decimal(iindex);// decial ix
        	int instype=de.decimal(itype);//decimal i
        	int insadd=de.decimal(iaddress);//decimal address
        	
        	
        	}


    // convert input into object values using switch case 

	}