package binaryCalculation;

public class GaloisField {
    static String irreducible = "10011";

    GaloisField(String a) {
        irreducible = a;
    }

    public static String product(String a, String b) {
        int[] x = toarrayint(a);
        int[] y = toarrayint(b);
        String[] rez = new String[y.length];
        String[] rezultati = new String[y.length];

        String d = "";
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < x.length; j++) {
                d = String.valueOf(x[x.length - 1 - j] * y[y.length - 1 - i]) + d;
            }
            rez[i] = d + generateZeros(i);
            d = "";
        }

        rezultati[0] = rez[0];
        for (int i = 1; i < rez.length; i++) {
            rezultati[i] = xOR(rezultati[i - 1], rez[i]);
        }
        rezultati[rezultati.length - 1] = reduce(rezultati[rezultati.length - 1]);
        return rezultati[rezultati.length - 1];

    }

    public static String xOR(String x, String y) {
        int m = Math.max(x.length(), y.length()) - Math.min(x.length(), y.length());
        String zeros = "";
        for (int i = 0; i < m; i++) {
            zeros += "0";
        }

        if (x.length() < y.length()) {
            x = zeros + x;
        } else {
            y = zeros + y;
        }

        int max = Math.max(x.length(), y.length());
        int X[] = new int[max];
        int Y[] = new int[max];
        int XorY[] = new int[max];
        String result = "";

        for (int i = 0; i < max; i++) {
            X[i] = Integer.valueOf("" + x.charAt(i));
            Y[i] = Integer.valueOf("" + y.charAt(i));
            XorY[i] = (X[i] + Y[i]) % 2;
            result = result + String.valueOf(XorY[i]);
        }

        while (result.length() > irreducible.length() - 1 && result.charAt(0) == '0') {
            result = result.substring(1);
        }
        return result;
    }

    public static String reduce(String x) {
        while (x.length() > irreducible.length() - 1) {
            String ireducible = irreducible;
            int m = x.length() - irreducible.length();
            String zeros = generateZeros(m);
            ireducible = ireducible + zeros; 
            x = xOR(x, ireducible);
        }
        return x;
    }

    public static int[] toarrayint(String x) {
        int[] rez = new int[x.length()];
        for (int i = 0; i < x.length(); i++) {
            rez[i] = Integer.valueOf(x.charAt(i) + "");
        }
        return rez;
    }

    public static String generateZeros(int z) {
        String zeros = "";
        for (int i = 0; i < z; i++) {
            zeros += "0";
        }
        return zeros;
    }
}
