package rsa;

import java.math.BigInteger;

public class RSA {

    /**
     * 
     * @param x         message
     * @param publicKey
     * @return encrypted message
     */
    public static BigInteger encrypt(BigInteger x, PublicKey publicKey) {
        BigInteger n = publicKey.getN();
        BigInteger e = publicKey.getE();
        BigInteger y = x.modPow(e, n);
        return y;
    }

    /**
     * 
     * @param y          encrypted message
     * @param privateKey
     * @return decrypted message
     */
    public static BigInteger decrypt(BigInteger y, PrivateKey privateKey) {
        BigInteger n = privateKey.getN();
        BigInteger d = privateKey.getD();
        BigInteger x = y.modPow(d, n);
        return x;
    }

    // Computes signature s = x^d mod n
    /**
     * 
     * @param x          message
     * @param privateKey
     * @return s = x^d mod n
     */
    public static BigInteger signature(BigInteger x, PrivateKey privateKey) {
        BigInteger n = privateKey.getN();
        BigInteger exponent = privateKey.getD();
        BigInteger s = x.modPow(exponent, n);
        return s;
    }

    /**
     * Computes x' = s^e mod n
     * 
     * @param s         signature
     * @param publicKey
     * @return x'
     */
    public static BigInteger signature(BigInteger s, PublicKey publicKey) {
        BigInteger n = publicKey.getN();
        BigInteger exponent = publicKey.getE();
        BigInteger x = s.modPow(exponent, n);
        return x;
    }

    public static BigInteger fi(BigInteger p, BigInteger q) {
        BigInteger one = new BigInteger("1");
        return (p.subtract(one)).multiply(q.subtract(one));
    }

}