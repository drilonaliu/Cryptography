import java.math.BigInteger;
import rsa.*;

public class RSA_Demo {
    public static void main(String[] args) {
        // Encrypting the number 17 and decrypting it
        BigInteger x = new BigInteger("17"); // x must be in Zn = {0,1,,,,n-1}
        BigInteger p = new BigInteger("3");
        BigInteger q = new BigInteger("11");
        PrivateKey privateKey = new PrivateKey(p, q);
        PublicKey publicKey = privateKey.getPublicKey();

        BigInteger cipherText = RSA.encrypt(x, publicKey);
        BigInteger decryptedText = RSA.decrypt(cipherText, privateKey);

        System.out.println("Encrypted with RSA: " + cipherText);
        System.out.println("Decrypted with RSA: " + decryptedText);
    }
}
