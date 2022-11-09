package SimplifiedAES;

import java.util.Map;

import binaryCalculation.BinaryArithmetic;
import binaryCalculation.GaloisField;

public class RoundKey {
    static String[][] SBox = {
        { "6", "B", "0", "4" },
        { "7", "E", "2", "F" },
        { "9", "8", "A", "C" },
        { "3", "1", "5", "D" } };

static Map<String, String> hex = BinaryArithmetic.getHexMap();

public static String[] addRoundKey(String[] K, int i) {
    int k0[], k1[], k2[], k3[] = new int[4];
    String[] result = new String[K.length];
    k0 = toNibbleArray(K[0]);
    k1 = toNibbleArray(K[1]);
    k2 = toNibbleArray(K[2]);
    k3 = toNibbleArray(K[3]);

    int[] g1 = g(k3, i);
    result[0] = GaloisField.xOR(arrayToString(k0), arrayToString(g1));
    result[1] = GaloisField.xOR(result[0], arrayToString(k1));
    result[2] = GaloisField.xOR(result[1], arrayToString(k2));
    result[3] = GaloisField.xOR(result[2], arrayToString(k3));

    return result;
}

public static int[] g(int[] b, int round) {

    int[] result = { 0, 0, 0, 0 };
    int[] d = { 0, 0, 0, 0 };

    int[] rc = rc(round);
    String s = SBox[2 * b[1] + b[2]][(2 * b[3] + b[0])];
    String a = hex.get(s);
    for (int i = 0; i < a.length(); i++) {
        d[i] = Integer.valueOf(a.charAt(i));
    }

    for (int i = 0; i < rc.length; i++) {
        result[i] = (d[i] + rc[i]) % 2;
    }
    return result;
}

public static int[] rc(int round) {
    if (round == 0) {
        int[] result = { 0, 0, 0, 1 };
        return result;

    } else if (round == 1) {
        int[] result = { 0, 0, 1, 0 };
        return result;

    } else {
        int[] result = { 0, 1, 0, 0 };
        return result;
    }
}

public static String arrayToString(int[] x) {
    String s = "";
    for (int i = 0; i < x.length; i++) {
        s += x[i] + "";
    }
    return s;
}

public static int[] toNibbleArray(String x) {
    //// 1010 e kthen ne array 1 0 1 0
    int[] result = new int[x.length()];
    for (int i = 0; i < x.length(); i++) {
        result[i] = Integer.valueOf("" + x.charAt(i));
    }
    return result;
}
}
