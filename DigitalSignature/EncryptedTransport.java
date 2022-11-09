package DigitalSignature;
import java.math.BigInteger;
import binaryCalculation.BinaryArithmetic;
import SimplifiedSHA.SHA;
import SimplifiedAES.SAES;
import rsa.*;

public class EncryptedTransport {

    private String encrypted;
    private int hash;
    private String aesKey;
    private BigInteger rsaSignature;
    private PublicKey publicKey;
    private SAES AES = new SAES();

    public EncryptedTransport(String message, String aesKey, rsa.PrivateKey privateKey) {
        this.aesKey = aesKey;
        this.publicKey = privateKey.getPublicKey();
        encrypted = AES.encrypt(message, aesKey);
        hash = Integer.parseInt(BinaryArithmetic.toBinary(SHA.hash(message)), 2);
        rsaSignature = RSA.signature(new BigInteger(hash + ""), privateKey);
    }

    public String getEncrypted() {
        return encrypted;
    }

    public int getHash() {
        return hash;
    }

    public BigInteger getRsaSignature() {
        return rsaSignature;
    }

    public String getAesKey() {
        return aesKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
