package BlockCiphers;

import SimplifiedAES.EncryptionAlgorithm;
import binaryCalculation.GaloisField;

//* * Counter Mode/
public class CTR extends BlockCipher {

    private String IV;

    public CTR(String IV, EncryptionAlgorithm ea) {
        super(ea);
        this.IV = IV;
    }

    @Override
    public String[] encrypt(String plainText, String key) {
        plainText = padding(plainText);
        String[] y = new String[plainText.length() / 16];
        String[] x = block16(plainText);
        int m = 16 - IV.length();
        String[] CTR = new String[y.length];
        for (int i = 0; i < y.length; i++) {
            CTR[i] = format(Integer.toBinaryString(i), m);
            y[i] = GaloisField.xOR(ea.encrypt(IV + CTR[i], key), x[i]);
        }
        return y;
    }

    @Override
    public String decrypt(String[] y, String key) {
        String[] x = new String[y.length];
        int m = 16 - IV.length();
        String[] CTR = new String[y.length];
        for (int i = 0; i < y.length; i++) {
            CTR[i] = format(Integer.toBinaryString(i), m);
            x[i] = GaloisField.xOR(ea.encrypt(IV + CTR[i], key), y[i]);
        }
        return arrayToString(x);
    }

}
