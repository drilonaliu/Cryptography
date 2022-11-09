package rsa;

import java.math.BigInteger;

public class Attack_RSA {

    public static void printAttack(PublicKey publicKey) {
        PrivateKey privateKey = fourthRoot(publicKey);
        System.out.println("Private Key paramtres are");
        System.out.println(privateKey);
        System.out.println("Is this key a valid key? " + privateKey.isValid());
    }

    public static PrivateKey fourthRoot(PublicKey publicKey) {
        BigInteger n = publicKey.getN();
        BigInteger e = publicKey.getE();
        BigInteger one = new BigInteger("1");
        BigInteger A = (n.sqrt()).add(one);
        BigInteger x = ((A.pow(2)).subtract(n)).sqrt();
        BigInteger p = A.subtract(x);
        BigInteger q = A.add(x);
        BigInteger fi = (p.subtract(one)).multiply(q.subtract(one));
        BigInteger d = e.modInverse(fi);
        return new PrivateKey(n, e, d, p, q, fi);
    }

}
