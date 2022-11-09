package HMAC;
import SimplifiedSHA.SHA;
import binaryCalculation.BinaryArithmetic;

/**
 * HMAC_Simplified_SHA
 * Hash Message Authentication Code (HMAC)
 */
public class HMAC_Simplified_SHA {

    // HMAC
    public static String hmac(String K, String m) {
        String mac = "";
        int B = 4;
        String ipad = repetition(BinaryArithmetic.toBinary("36"), B);
        String opad = repetition(BinaryArithmetic.toBinary("5C"), B);
        K = expandKey(K);
        String A = BinaryArithmetic.xOR(K, ipad);
        String D = BinaryArithmetic.toBinary(SHA.hash(BinaryArithmetic.xOR(K, opad) + m));
        mac = SHA.hash(A + D);
        return mac;
    }

    public static String expandKey(String Key) {
        if (Key.length() > 32) {
            return BinaryArithmetic.toBinary(SHA.hash(Key));
        } else {
            return BinaryArithmetic.generateZeros(32 - Key.length()) + Key;
        }
    }

    public static String repetition(String a, int x) {
        String r = "";
        for (int i = 0; i < x; i++) {
            r = r + a;
        }
        return r;
    }

}