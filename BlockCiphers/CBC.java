package BlockCiphers;

import SimplifiedAES.EncryptionAlgorithm;
import binaryCalculation.GaloisField;

public class CBC extends BlockCipher {

    private String[] x;
    private String[] y;
    private String IV;

    public CBC(String IV, EncryptionAlgorithm ea) {
        super(ea);
        this.IV = IV;
    }

    public String[] encrypt(String plainText, String key) {
        plainText = padding(plainText);
        y = new String[plainText.length() / 16];
        x = block16(plainText);
        y[0] = ea.encrypt(GaloisField.xOR(x[0], IV), key);
        for (int i = 1; i < y.length; i++) {
            y[i] = ea.encrypt(GaloisField.xOR(x[i], y[i - 1]), key);
        }
        return y;
    }

    @Override
    public String decrypt(String[] y, String key) {
        String[] x = new String[y.length];
        x[0] = GaloisField.xOR(ea.decrypt(y[0], key), IV);
        for (int i = 1; i < x.length; i++) {
            x[i] = GaloisField.xOR(ea.decrypt(y[i], key), y[i - 1]);
        }
        return arrayToString(x);
    }

    public static void printStringArray(String[] a) {

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
