import HMAC.HMAC_Simplified_SHA;
import binaryCalculation.BinaryArithmetic;

/**
 * HMAC_Demo
 */
public class HMAC_Demo {

    public static void main(String[] args) {
        String m = BinaryArithmetic.toBinary("AAAAAAAAAAA");// hex
        String k = BinaryArithmetic.toBinary("BBBBBBBB"); // hex
        System.out.println(HMAC_Simplified_SHA.hmac(k, m));
    }
}