package SimplifiedAES;

public interface EncryptionAlgorithm {
    public String encrypt(String plainText, String Key);
    public String decrypt(String cipherText, String Key);
}
