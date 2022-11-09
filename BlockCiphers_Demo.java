import BlockCiphers.*;
import SimplifiedAES.*;

/**
 * Encrypting the plainText with the Key with block ciphers using Simplified AES
 * Decrypted text will be the same as plain text
 */

public class BlockCiphers_Demo {
    public static void main(String[] args) {

        String key = "101011";
        String plainText = "101110001001001010111000100100";
        String IV = "1010001011010110";

        // AES
        EncryptionAlgorithm sAES = new SAES();
        // System.out.println(sAES.decrypt(sAES.encrypt("11100", "101011"), "101011"));

        // ECB
        BlockCipher ecb = new ECB(sAES);
        String[] encrypted = ecb.encrypt(plainText, key);
        System.out.println("Decrypted with ECB: " + ecb.decrypt(encrypted, key));

        // CBC
        BlockCipher cbc = new CBC(IV, sAES);
        String[] encrypted1 = cbc.encrypt(plainText, key);
        // System.out.println(encrypted1);
        System.out.println("Decrypted with CBC: " + cbc.decrypt(encrypted1, key));

        // OFB
        BlockCipher ofb = new OFB(IV, sAES);
        String[] encrypted2 = ofb.encrypt(plainText, key);
        System.out.println("Decrypted with OFB: " + ofb.decrypt(encrypted2, key));

        // CFM
        BlockCipher cfm = new CFM(IV, sAES);
        String[] encrypted3 = cfm.encrypt(plainText, key);
        System.out.println("Decrypted with CFM: " + cfm.decrypt(encrypted3, key));

        // CTR
        BlockCipher ctr = new CTR(IV, sAES);
        String[] encrypted4 = ctr.encrypt(plainText, key);
        System.out.println("Decrypted with CTR: " + ctr.decrypt(encrypted4, key));

        // GCM
        BlockCipher gcm = new GCM(IV, sAES);
        String[] enctrypted4 = gcm.encrypt(plainText, key);
        System.out.println("Decrypted with GCM: " + gcm.decrypt(enctrypted4, key));
    }
}
