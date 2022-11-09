package BlockCiphers;

import SimplifiedAES.EncryptionAlgorithm;

/* 
 * Electronic Code Book
 */
public class ECB extends BlockCipher {

    private String[] x;
    private String[] y;

    public ECB(EncryptionAlgorithm ea) {
        super(ea);
    }

    @Override
    public String[] encrypt(String plainText, String key) {
        while (plainText.charAt(0) == '0') {
            plainText = plainText.substring(1);
        }
        plainText = padding(plainText);
        y = new String[plainText.length() / 16];
        x = block16(plainText);
        for (int i = 0; i < y.length; i++) {
            y[i] = ea.encrypt(x[i], key);
        }
        return y;
    }

    @Override
    public String decrypt(String[] y, String key) {
        String result = "";
        for (int i = 0; i < x.length; i++) {
            x[i] = ea.decrypt(y[i], key);
            result = result + x[i];
        }
        while (result.charAt(0) == '0') {
            result = result.substring(1);
        }
        return result;
    }
}
