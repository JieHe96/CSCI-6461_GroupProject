
public class Decode<string> {
    /**
     * fetch certain bits of a 16bits number
     * @param value 16bits data
     * @param start high bit
     * @param end	low bit
     * @return		value of value[start:end]
     */
<<<<<<< Updated upstream
    /**gettin the values from the instruction set */ 
	
	static private int getBit(int value, int start, int end) {
=======
    /**gettin the values from the instruction set */

    static private int getBit(int value, int start, int end) {
>>>>>>> Stashed changes
        return value << (15-start) >>> (15-start+end);
    }

    //decode

<<<<<<< Updated upstream
    public int iregister, iindex, itype, iaddress, hh;

    public void phaseDecode() throws Exception {

        // Instruction register 
        int ir = regs.getReg(regs.IR);

        // decode，op = ir[5:0] 
        //operand part of the instruction
        int opbinary    = getBit(ir, 5, 0);
=======
    public string iregister, iindex, itype, iaddress, hh;

    public void phaseDecode() throws Exception {

        // Instruction register
        int ir = regs.getReg(regs.IR);

        // decode，op = ir[5:0]
        //operand part of the instruction



        //int op = Integer.parseInt(getBit(ir, 5, 0),2);

        iregister = ir.substring(6,7);//r
        iindex = ir.substring(8,9);;//ix
        itype = ir.substring(10);;//i
        iaddress = ir.substring(11,15);;//ad

>>>>>>> Stashed changes

        iregister = getBit(ir, 7, 6);//r
         iindex = getBit(ir, 9, 8);//ix
        itype = getBit(ir, 10, 10);//i
        iaddress = getBit(ir, 15, 11);//ad
        
         /*switch (op) {
        //creating switch cases for instruction types 

<<<<<<< Updated upstream
            case 0x01: LDR();    break;
            case 0x02: STR();    break;
            case 0x03: LDA();    break;
            case 0x29: LDX();    break;
            case 0x2A: STX();    break;
*/
=======
        switch (op) {
            //creating switch cases for instruction types

            case 01: LDR();    break;
            case 02: STR();    break;
            case 03: LDA();    break;
            case 41: LDX();    break;
            case 42: STX();    break;


>>>>>>> Stashed changes
        }

        //print("  ^ s=" + ss + " t=" + tt + " d=" + dd + " h=" + hh + " i=" + ii);
    }

    public void BinaryToDecimal(){
        int decimal = Interger.parseInt(iregister,2);
        int decimal = Interger.parseInt(iregister,2);

    }
}

