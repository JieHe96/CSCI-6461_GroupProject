
public class Decode {
    /**
     * fetch certain bits of a 16bits number
     * @param value 16bits data
     * @param start high bit
     * @param end	low bit
     * @return		value of value[start:end]
     */
    static private int getBit(int value, int start, int end) {
        return value << (15-start) >>> (15-start+end);
    }

    //decode

    private int ss, tt, ii, dd, hh;

    private void phaseDecode() throws Exception {

        // IR
        int ir = regs.getReg(regs.IR);

        // decode，op = ir[5:0]
        int op    = getBit(ir, 5, 0);


        switch (op) {

            ss = getBit(ir, 7, 6);//r
            tt = getBit(ir, 9, 8);//ix
            ii = getBit(ir, 10, 10);//i
            dd = getBit(ir, 15, 11);//ad
            case 0x01: LDR();    break;
            case 0x02: STR();    break;
            case 0x03: LDA();    break;
            case 0x29: LDX();    break;
            case 0x2A: STX();    break;


        }

        //print("  ^ s=" + ss + " t=" + tt + " d=" + dd + " h=" + hh + " i=" + ii);
    }
    }


