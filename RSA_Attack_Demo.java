import java.math.BigInteger;
import rsa.Attack_RSA;
import rsa.PublicKey;

public class RSA_Attack_Demo {

    public static void main(String[] args) {

        /*
         * Bob generates a private key, but he is not careful because he chooses p and q
         * such that |p-q|<2n^(1/4).
         * The public key is (797306344204135429053419,920419823)
         * Show that we can find the private key by the fourth root attack on RSA.
         */

        BigInteger n = new BigInteger("797306344204135429053419");
        BigInteger e = new BigInteger("920419823");
        PublicKey publicKey2 = new PublicKey(n, e);
        Attack_RSA.printAttack(publicKey2);

    }
}
