package BlockCiphers;

import SimplifiedAES.EncryptionAlgorithm;
import binaryCalculation.GaloisField;

public abstract class BlockCipher {
    protected EncryptionAlgorithm ea;

    public BlockCipher(EncryptionAlgorithm ea) {
        this.ea = ea;
    }
    
    public abstract String[] encrypt(String plainText, String key);

    public abstract String decrypt(String[] cipherText, String key);

    protected String padding(String x) {
        while (x.length() % 16 != 0) {
            x = GaloisField.generateZeros(16 - x.length() % 16) + x;
        }
        return x;
    }

    protected String[] block16(String text) {
        String[] rezult = new String[text.length() / 16];
        for (int i = 0; i < rezult.length; i = i + 1) {
            rezult[i] = text.substring(16 * i, 16 * (i + 1));
        }
        return rezult;
    }

    protected String arrayToString(String[] x) {
        String y = "";
        for (int i = 0; i < x.length; i++) {
            y = y+  x[i];
        }
        return y;
    }

    protected String format(String x, int m) {
        if (x.length() < m) {
            x = GaloisField.generateZeros(m - x.length()) + x;
        }
        return x;
    }

    public EncryptionAlgorithm getEA() {
        return ea;
    }

}
