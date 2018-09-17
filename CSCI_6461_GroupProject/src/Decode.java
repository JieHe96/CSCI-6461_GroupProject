
public class Decode<string> {
    /**
     * fetch certain bits of a 16bits number
     * @param value 16bits data
     * @param start high bit
     * @param end	low bit
     * @return		value of value[start:end]
     */

    /**gettin the values from the instruction set */ 
	


    static private int getBit(int value, int start, int end) {
        return value << (15-start) >>> (15-start+end);
    }

    //decode

    public void ToDecimal(String Str)
    {
        return ( Integer.parseInt(Str,2);
    }

    
    public int iregister, iindex, itype, iaddress, hh;

    public void phaseDecode() throws Exception {

        // Instruction register 
        int ir = regs.getReg(regs.IR);

        // decode，op = ir[5:0] 
        //operand part of the instruction
        int opbinary    = getBit(ir, 5, 0);

    public string iregister, iindex, itype, iaddress, hh;

    public void phaseDecode() throws Exception {

        // Instruction register
        int ir = regs.getReg(regs.IR);

        // decode，op = ir[5:0]
        //operand part of the instruction


       /* iregister = getBit(ir, 7, 6);//r
         iindex = getBit(ir, 9, 8);//ix
        itype = getBit(ir, 10, 10);//i
        iaddress = getBit(ir, 15, 11);//ad
        */
        
         /*switch (op) {
        //creating switch cases for instruction types 


        switch (op) {
            //creating switch cases for instruction types

            case 01: LDR();    break;
            case 02: STR();    break;
            case 03: LDA();    break;
            case 41: LDX();    break;
            case 42: STX();    break;


        }

        //print("  ^ s=" + ss + " t=" + tt + " d=" + dd + " h=" + hh + " i=" + ii);
    }

  
    public void ToBinary(){
        int rBinary = Integer.toBinaryString(rDecimal));
        int ixBinary = Integer.toBinaryString(ixdecimal));
        int iBinary = Integer.toBinaryString(iDecimal));
        int adBinary = Integer.toBinaryString(adDecimal));

    }
}

}
}
