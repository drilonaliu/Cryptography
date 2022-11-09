package SimplifiedAES;

import java.util.Map;
import binaryCalculation.GaloisField;
import binaryCalculation.BinaryArithmetic;

public class SAES implements EncryptionAlgorithm {

    private String[][] SBox = {
            { "6", "B", "0", "4" },
            { "7", "E", "2", "F" },
            { "9", "8", "A", "C" },
            { "3", "1", "5", "D" } };

    private String[][] invSBox = {
            { "2", "D", "6", "C" },
            { "3", "E", "0", "4" },
            { "9", "8", "A", "1" },
            { "B", "F", "5", "7" } };

    private Map<String, String> hex = BinaryArithmetic.getHexMap();

    public String encrypt(String plainText, String key) {
        String[] P = fill(plainText);
        String[] K = fill(key);
        String[] B= xOrMatrix(P, K);
        String[] D = { "", "", "", "" };

        String[] C = B;
        for (int i = 0; i < 3; i++) {
            C = substitute(C, SBox);
            C = shiftRows(C);
            if (i < 2) {
                D = mixColumns(C);
            }
            if (i == 2) {
                D = C;
            }
            K = RoundKey.addRoundKey(K, i);
            C = xOrMatrix(D, K);
        }

        String result = "";
        for (int i = 0; i < C.length; i++) {
            result += C[i];
        }
        return result;
    }

    public String decrypt(String Cipher, String key) {

        String[] Encrypted = fill(Cipher);
        String[] K0 = fill(key);

        String[] K1 = RoundKey.addRoundKey(K0, 0);
        String[] K2 = RoundKey.addRoundKey(K1, 1);
        String[] K3 = RoundKey.addRoundKey(K2, 2);

        String[] B;
        String[] C;

        B = xOrMatrix(Encrypted, K3);
        B = shiftRows(B);
        C = substitute(B, invSBox);

        String[] T = C;

        String[] D = { "", "", "", "" };
        String[] P = { "", "", "", "" };
        String[] K = { "", "", "", "" };

        for (int i = 1; i < 3; i++) {
            if (i == 1) {
                K = K2;
            }
            if (i == 2) {
                K = K1;
            }
            T = xOrMatrix(T, K);

            D[0] = GaloisField.xOR(GaloisField.product(hex.get("F"), T[0]), GaloisField.product(hex.get("E"), T[1]));
            D[1] = GaloisField.xOR(GaloisField.product(hex.get("E"), T[0]), GaloisField.product(hex.get("E"), T[1]));
            D[2] = GaloisField.xOR(GaloisField.product(hex.get("F"), T[2]), GaloisField.product(hex.get("E"), T[3]));
            D[3] = GaloisField.xOR(GaloisField.product(hex.get("E"), T[2]), GaloisField.product(hex.get("E"), T[3]));

            D = shiftRows(D);
            P = substitute(D, invSBox);
            T = P;
        }

        String L[] = xOrMatrix(T, K0);

        String result = "";
        for (int i = 0; i < L.length; i++) {
            result = result + L[i];
        }
        return result;
    }

    // Text xOr Key
    private static String[] xOrMatrix(String[] x, String[] y) {
        String c[] = new String[4];
        for (int i = 0; i < 4; i++) {
            c[i] = GaloisField.xOR(x[i], y[i]);
        }
        return c;
    }

    // SBox substitution
    private String[] substitute(String[] B, String[][] Box) {
        int[] b;
        String[] C = new String[4];
        for (int i = 0; i < 4; i++) {
            b = (toNibbleArray(B[i])); // kur b[i] = "0101", b = 0 1 0 1
            String s = hex.get(Box[2 * b[0] + b[1]][(2 * b[2] + b[3])]); // konvertojme menihere prej binare ne nr
            C[i] = s;
        }
        return C;
    }

    // ShiftRows
    private String[] shiftRows(String[] C) {
        String temp1 = C[3];
        String temp2 = C[1];
        C[1] = temp1;
        C[3] = temp2;
        return C;
    }

    // Mix coloumns
    private String[] mixColumns(String[] C) {

        String D[] = new String[4];
        D[0] = GaloisField.xOR(C[0], C[1]); // c0 dhe c1 gjatesi nga kater prandaj d0 eshte gjithmone gjatesi4
        D[1] = GaloisField.xOR(C[0], C[1] + "0"); // sepse 2 * c3 = (0010) *(abcd) = abcd0 dmth shiftim per nje
        D[2] = GaloisField.xOR(C[2], C[3]); // gjatesi 4
        D[3] = GaloisField.xOR(C[2], C[3] + "0");

        D[1] = GaloisField.reduce(D[1]);
        D[3] = GaloisField.reduce(D[3]);

        return D;
    }

    // Metodat ndihmese
    private static int[] toNibbleArray(String x) {
        //// 1010 e kthen ne array 1 0 1 0
        int[] result = new int[x.length()];
        for (int i = 0; i < x.length(); i++) {
            result[i] = Integer.valueOf("" + x.charAt(i));
        }
        return result;
    }

    // "16 bit
    private static String[] fill(String a) {
        String x = a + "";
        String zeros = "";
        if (x.length() < 16) {
            for (int i = 0; i < 16 - x.length(); i++) {
                zeros += "0";
            }
        }

        x = zeros + x;
        String X[] = { "", "", "", "" };

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                X[j] = X[j] + x.charAt(4 * j + i);
            }

        }
        return X;
    }

}
