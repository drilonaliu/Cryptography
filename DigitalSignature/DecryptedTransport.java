package DigitalSignature;
import java.math.BigInteger;

import SimplifiedAES.SAES;
import rsa.RSA;

public class DecryptedTransport {

    private SAES AES = new SAES();
    private EncryptedTransport encryptedTransport;
    private String decrypted;
    private BigInteger rsaSignature;

    public DecryptedTransport(EncryptedTransport encryptedTransport) {
        this.encryptedTransport = encryptedTransport;
        decrypted = AES.decrypt(encryptedTransport.getEncrypted(), encryptedTransport.getAesKey());
        rsaSignature = RSA.signature(encryptedTransport.getRsaSignature(), encryptedTransport.getPublicKey());
    }

    DecryptedTransport(String decrypted, BigInteger rsaSignature) {
        this.decrypted = decrypted;
        this.rsaSignature = rsaSignature;
    }

    public boolean isValid() {
        return (this.rsaSignature.equals(new BigInteger(encryptedTransport.getHash() +"")));
    }

    public String getDecrypted() {
        return decrypted;
    }

    public BigInteger getRsaSignature() {
        return rsaSignature;
    }

    public EncryptedTransport getEncryptedTransport() {
        return encryptedTransport;
    }

}
