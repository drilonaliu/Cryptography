
package SimplifiedSHA;

import binaryCalculation.BinaryArithmetic;

/**
 * Simplified SHA1
 */
public class SHA {
    static final String[] registers = { "45", "AF", "AC", "FE" };
    static String[] W = new String[16];

    // Hashing
    public static String hash(String message) {
        String hash = "";
        String[] r = registers.clone();
        String H[] = new String[4];
        for (int i = 0; i < 4; i++) {
            r[i] = BinaryArithmetic.toBinary(r[i]);
            H[i] = r[i];
        }
        message = padding(message);

        String[] x = divide(message, 32);
        for (int i = 0; i < x.length; i++) {
            r = H.clone();

            for (int j = 0; j < 16; j++) {
                String[] y = divide(x[i], 8);
                if (j <= 3) {
                    W[j] = y[j];
                } else {
                    W[j] = rotoate(BinaryArithmetic.xOR(W[j - 4], W[j - 2]), 2);
                }
                r = round(r, W[j], t(j));
            }

            for (int z = 0; z < 4; z++) {
                H[z] = square(r[z], H[z]);
            }
        }
        hash = BinaryArithmetic.toHex(asWord(H));
        return hash;
    }

    // Round
    private static String[] round(String[] r, String W, int i) {
        String[] K = { "5A", "E7", "8C", "BD" };
        for (int z = 0; z < 4; z++) {
            K[z] = BinaryArithmetic.toBinary(K[z]);
        }
        String A = r[0];
        String B = r[1];
        String C = r[2];
        String D = r[3];
        String rez = square(square(square(square(f(i, B, C), D), rotoate(A, 3)), W), K[i]);
        D = C;
        C = rotoate(B, 7);
        B = A;
        A = rez;
        r[0] = A;
        r[1] = B;
        r[2] = C;
        r[3] = D;
        return r;
    }

    // Addition modulo 2^8
    private static String square(String x, String y) {
        int X = Integer.parseInt(x, 2);
        int Y = Integer.parseInt(y, 2);
        int z = (X + Y) % 256;
        String d = Integer.toBinaryString(z);
        String c = BinaryArithmetic.generateZeros(8 - d.length()) + d;
        return c;
    }

    // Function
    private static String f(int i, String B, String C) {
        switch (i) {
            case 0:
                return BinaryArithmetic.and(B, C);
            case 1:
                return BinaryArithmetic.xOR(B, C);
            case 2:
                return BinaryArithmetic.xOR(B, BinaryArithmetic.not(C));
            case 3:
                return BinaryArithmetic.xOR(B, C);
            default:
                return "";
        }
    }

    // Shifting
    private static String rotoate(String x, int n) {
        x = x.substring(n) + x.substring(0, n);
        return x;
    }

    // Padding
    private static String padding(String message) {
        int k = (32 - 16 - message.length() - 1) % 32;
        if (k < 0) {
            k = k % 32 + 32;
        }
        String p = Integer.toBinaryString(message.length());
        p = BinaryArithmetic.generateZeros(16 - p.length()) + p;// gjatesia me qene 16
        message = message + "1" + BinaryArithmetic.generateZeros(k) + p;
        return message;
    }

    private static int t(int j) {
        if (j < 4) {
            return 0;
        } else if (j < 8) {
            return 1;
        } else if (j < 12) {
            return 2;
        } else
            return 3;
    }

    // Divide
    private static String[] divide(String message, int k) {
        int size = message.length() / k;
        String[] x = new String[size];

        for (int i = 0; i < x.length; i++) {
            x[i] = message.substring(k * i, k * (i + 1));
        }
        return x;
    }

    // asWord - kthen si String nje array
    private static String asWord(String[] H) {
        String word = "";
        for (int i = 0; i < H.length; i++) {
            word = word + H[i];
        }
        return word;
    }
}