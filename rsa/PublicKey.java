package rsa;

import java.math.BigInteger;

public class PublicKey {

    private BigInteger n;
    private BigInteger e;

    public PublicKey(BigInteger n, BigInteger e) {
        this.n = n;
        this.e = e;
    }

    public PublicKey(PrivateKey privateKey){
        n = privateKey.getN();
        e = privateKey.getE();
    }
    @Override
    public String toString() {
        String output = "n = " + n + " \ne = " + e;
        return output;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj instanceof PublicKey) {
            PublicKey publicKey = (PublicKey) obj;
            if (n.equals(publicKey.getN()) && e.equals(publicKey.getE())) {
                isEqual = true;
            }
        }
        return isEqual;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getE() {
        return e;
    }

}
