package BlockCiphers;

import SimplifiedAES.EncryptionAlgorithm;
import binaryCalculation.GaloisField;

//* Cipher Feedback Mode */
public class CFM extends BlockCipher {

    private String IV;

    public CFM(String IV, EncryptionAlgorithm ea) {
        super(ea);
        this.IV = IV;
    }

    @Override
    public String[] encrypt(String plainText, String key) {
        plainText = padding(plainText);
        String[] y = new String[plainText.length() / 16];
        String[] x = block16(plainText);

        y[0] = GaloisField.xOR(ea.encrypt(IV, key), x[0]);
        for (int i = 1; i < y.length; i++) {
            y[i] = GaloisField.xOR(ea.encrypt(y[i - 1], key), x[i]);
        }
        return y;
    }

    @Override
    public String decrypt(String[] y, String key) {
        String[] x = new String[y.length];
        x[0] = GaloisField.xOR(ea.encrypt(IV, key), y[0]);

        for (int i = 1; i < y.length; i++) {
            x[i] = GaloisField.xOR(ea.encrypt(y[i - 1], key), y[i]);
        }
        return arrayToString(x);
    }

}
