package binaryCalculation;

import java.util.LinkedHashMap;
import java.util.Map;

public class BinaryArithmetic {

    // or
    public static String or(String x, String y) {
        String[] values = addZeros(x, y);
        x = values[0];
        y = values[1];
        String sum = "";
        int d = 0;
        for (int i = 0; i < x.length(); i++) {
            d = Integer.valueOf("" + x.charAt(i)) + Integer.valueOf("" + y.charAt(i));
            if (d == 2) {
                d = 1;
            }
            sum = sum + String.valueOf(d);
        }
        return sum;
    }

    // and
    public static String and(String x, String y) {
        String[] values = addZeros(x, y);
        x = values[0];
        y = values[1];

        String product = "";
        int d = 0;

        for (int i = 0; i < x.length(); i++) {
            d = Integer.valueOf(x.charAt(i) + "") * Integer.valueOf(y.charAt(i) + "");
            product = product + d;
        }
        return product;
    }

    // not
    public static String not(String x) {
        String d = "";
        for (int i = 0; i < x.length(); i++) {
            char c = x.charAt(i);
            if (c == '0') {
                d = d + '1';
            } else {
                d = d + '0';
            }
        }
        return d;
    }

    // xOr
    public static String xOR(String x, String y) {
        String[] values = addZeros(x, y);
        x = values[0];
        y = values[1];

        int length = x.length();
        String result = "";
        int a, b, c;
        for (int i = 0; i < length; i++) {
            a = Integer.valueOf("" + x.charAt(i));
            b = Integer.valueOf("" + y.charAt(i));
            c = (a + b) % 2;
            result = result + String.valueOf(c);
        }
        return result;
    }

    public static String[] addZeros(String x, String y) {
        int m = (Math.max(x.length(), y.length())) - (Math.min(x.length(), y.length()));
        String zeros = generateZeros(m);

        if (x.length() < y.length()) {
            x = zeros + x;
        } else {
            y = zeros + y;
        }
        return new String[] { x, y };
    }

    public static Map<String, String> getHexMap() {
        Map<String, String> hex = new LinkedHashMap<>();
        hex.put("0", "0000");
        hex.put("1", "0001");
        hex.put("2", "0010");
        hex.put("3", "0011");
        hex.put("4", "0100");
        hex.put("5", "0101");
        hex.put("6", "0110");
        hex.put("7", "0111");
        hex.put("8", "1000");
        hex.put("9", "1001");
        hex.put("A", "1010");
        hex.put("B", "1011");
        hex.put("C", "1100");
        hex.put("D", "1101");
        hex.put("E", "1110");
        hex.put("F", "1111");
        return hex;
    }

    public static String generateZeros(int z) {
        String zeros = "";
        for (int i = 0; i < z; i++) {
            zeros += "0";
        }
        return zeros;
    }

    public static String toBinary(String d) {
        Map<String, String> h = BinaryArithmetic.getHexMap();
        String bin = "";
        for (int i = 0; i < d.length(); i++) {
            bin = bin + h.get(d.charAt(i) + "");
        }
        return bin;
    }

    public static String toHex(String d) {
        Map<String, String> binary = new LinkedHashMap<>();
        Map<String, String> hex = BinaryArithmetic.getHexMap();
        hex.forEach((a, b) -> {
            binary.put(b, a);
        });

        String word = "";
        for (int i = 0; i < d.length() / 4; i++) {
            word = word + binary.get(d.substring(4 * i, 4 * (i + 1)));
        }
        return word;
    }
}
