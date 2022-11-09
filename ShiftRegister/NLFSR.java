package ShiftRegister;

/* Non Linear FeedBack Shift Register 
 */
public class NLFSR {
    private int[] A = new int[11];
    private int[] B = new int[21];
    private int[] C = new int[29];
    private int[] s = new int[120];
    private int a, b, c;
    private int and1, and2, and3;

    public NLFSR(int[] IV, int[] KEY) {

        for (int i = 0; i < 10; i++) {
            A[i] = IV[i];
            B[i] = KEY[i];
            C[i] = (IV[i] + KEY[i]) % 2; // iv xOr key
        }

        for (int i = 0; i < s.length; i++) {

            and1 = and(A[4], A[10]);
            a = xOr(and1, xOr(A[1], A[6]));

            B[2] = a;
            B[16] = and1;
            and2 = and(B[9], B[13]);
            b = xOr(and2, xOr(B[16], B[20]));

            C[27] = and3;
            and3 = and(C[19], C[22]);
            c = xOr(and3, xOr(C[28], C[27]));

            A[0] = xOr(A[2], B[1]);
            B[0] = xOr(B[1], xOr(and2, C[14]));
            C[0] = xOr(C[1], and3);

            s[i] = xOr(xOr(a, b), c);

            A = shift(A, s[i]);
            B = shift(B, s[i]);
            C = shift(C, s[i]);

        }

    }

    public int[] getS() {
        return s;
    }

    private int[] shift(int[] x, int y) {
        int[] a = new int[x.length];
        a[0] = y;

        for (int i = 1; i < a.length; i++) {
            a[i] = x[i - 1];
        }
        return a;
    }

    private int and(int x, int y) {
        return x * y;
    }

    private int xOr(int x, int y) {
        return (x + y) % 2;
    }

}