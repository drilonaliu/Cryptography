package rsa;

import java.math.BigInteger;

public class PrivateKey {
    
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;
    private BigInteger p;
    private BigInteger q;
    private BigInteger phi;

    /**
     * Constructs the private key based on the prime numbers p,q.
     * n=p*q
     * sets e,d such that e*d = 1 mod fi(n);
     * 
     * @param p prime number p
     * @param q prime number q
     */
    public PrivateKey(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
        generateKey();
    }

    /**
     * Use this constructor if you already know the values for n, e, and d
     * Make sure that e*d= 1 mod fi(n)
     * p and q values will be unknown
     * 
     * @param n
     * @param e
     * @param d
     */
    public PrivateKey(BigInteger n, BigInteger e, BigInteger d) {
        this.n = n;
        this.e = e;
        this.d = d;
    }

    public PrivateKey(BigInteger n, BigInteger e, BigInteger d, BigInteger p, BigInteger q, BigInteger phi) {
        this.n = n;
        this.e = e;
        this.d = d;
        this.p = p;
        this.q = q;
        this.phi = phi;
    }

    /**
     * Finds and sets values e and d such that e*d=1 mod phi(n)
     */
    private void generateKey() {
        n = p.multiply(q);
        BigInteger m = fi(p, q);
        e = new BigInteger("0");

        BigInteger i1 = new BigInteger("0");
        BigInteger i = new BigInteger("2");

        boolean stop = false;
        while (!stop) {
            i = i.add(BigInteger.ONE);
            i1 = new BigInteger(String.valueOf(i));
            if (((m.gcd(i1)).compareTo(BigInteger.ONE) == 0)) {
                e = i1;
                stop = true;
            }
        }
        d = e.modInverse(m);
    }

    /**
     * Phi Euler function for prime numbers p and q.
     * 
     * @param p prime number p
     * @param q prime number q
     * @return (p-1)(q-1) = fi(n)
     */
    private BigInteger fi(BigInteger p, BigInteger q) {
        BigInteger one = new BigInteger("1");
        return (p.subtract(one)).multiply(q.subtract(one));
    }

    /**
     * Checks if the private key parametres are valid
     * 
     * @return if p*q =n and d*e =1 mod phi(n)
     */
    public boolean isValid() {
        return ((p.multiply(q)).equals(n) && (d.multiply(e).mod(phi)).equals(BigInteger.ONE));
    }

    @Override
    public String toString() {
        String output = "n = " + n + "\ne = " + e + "\nd = " + d + "\np = " + p + "\nq =" + q + "\nphi = " + phi;
        return output;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj instanceof PrivateKey) {
            PrivateKey privateKey = (PrivateKey) obj;
            if (n.equals(privateKey.getN()) && e.equals(privateKey.getE())
                    && d.equals(privateKey.getD())) {
                isEqual = true;
            }

        }
        return isEqual;
    }

    public PublicKey getPublicKey() {
        return new PublicKey(n, e);
    }

    /**
     * 
     * @return d from equation e*d=1 mod phi(n)
     */
    public BigInteger getD() {
        return d;
    }

    /**
     * 
     * @return e from equation e*d=1 mod phi(n)
     */
    public BigInteger getE() {
        return e;
    }

    /**
     * 
     * @return n = p*q
     */
    public BigInteger getN() {
        return n;
    }

    /**
     * 
     * @return prime number p
     */
    public BigInteger getP() {
        return p;
    }

    /**
     * 
     * @return prime number q
     */
    public BigInteger getQ() {
        return q;
    }

    public BigInteger getPhi() {
        return phi;
    }
}
