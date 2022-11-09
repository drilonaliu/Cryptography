package BlockCiphers;

import SimplifiedAES.EncryptionAlgorithm;
import binaryCalculation.GaloisField;

/* Galouis Counter Mode */
public class GCM extends BlockCipher {

    String IV;

    public GCM(String IV, EncryptionAlgorithm ea) {
        super(ea);
        this.IV = IV;
    }

    @Override
    public String[] encrypt(String plainText, String key) {
        plainText = padding(plainText);
        String[] y = new String[plainText.length() / 16];
        String[] x = block16(plainText);
        String[] CTR = new String[y.length];

        for (int i = 0; i < y.length; i++) {
            CTR[i] = format(Integer.toBinaryString(i + 1), 16);
            y[i] = GaloisField.xOR(ea.encrypt(CTR[i], key), x[i]);
        }

        return y;
    }

    @Override
    public String decrypt(String[] y, String key) {
        String[] x = new String[y.length];
        String[] CTR = new String[y.length];

        for (int i = 0; i < y.length; i++) {
            CTR[i] = format(Integer.toBinaryString(i + 1), 16);
            x[i] = GaloisField.xOR(ea.encrypt(CTR[i], key), y[i]);
        }
        return arrayToString(x);
    }

}
