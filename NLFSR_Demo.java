import ShiftRegister.NLFSR;

public class NLFSR_Demo {
    public static void main(String[] args) {
        int[] IV = { 1, 1, 1, 0, 0, 1, 1, 0, 1, 0 };
        int[] KEY = { 1, 0, 1, 0, 1, 1, 0, 1, 1, 1 };
        int[] d = new NLFSR(IV, KEY).getS();

        for (int i = 0; i < d.length; i++) {
            System.out.print(d[i]);
        }

    }
}
