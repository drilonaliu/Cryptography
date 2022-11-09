package BlockCiphers;

import SimplifiedAES.EncryptionAlgorithm;
import binaryCalculation.GaloisField;

/* Output Feedback */
public class OFB extends BlockCipher {

    private String IV;

    public OFB(String IV, EncryptionAlgorithm ea) {
        super(ea);
        this.IV = IV;
    }

    @Override
    public String[] encrypt(String plainText, String key) {

        plainText = padding(plainText);
        String[] y = new String[plainText.length() / 16];
        String[] x = block16(plainText);

        String[] s = new String[y.length];
        s[0] = ea.encrypt(IV, key);
        y[0] = GaloisField.xOR(s[0], x[0]);

        for (int i = 1; i < y.length; i++) {
            s[i] = ea.encrypt(s[i - 1], key);
            y[i] = GaloisField.xOR(s[i], x[i]);
        }
        return y;
    }

    @Override
    public String decrypt(String[] y, String key) {
        String[] s = new String[y.length];
        String[] x = new String[s.length];
        s[0] = ea.encrypt(IV, key);
        x[0] = GaloisField.xOR(s[0], y[0]);
        for (int i = 1; i < y.length; i++) {
            s[i] = ea.encrypt(s[i - 1], key);
            x[i] = GaloisField.xOR(s[i], y[i]);
        }
        return arrayToString(x);
    }

}
