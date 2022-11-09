/**
 * Digital Signature Scheme demonstration
 * 
 */

import java.math.BigInteger;
import DigitalSignature.DecryptedTransport;
import DigitalSignature.EncryptedTransport;
import rsa.*;

public class DigitalSignature_Demo {

    public static void main(String[] args) {

        // Message
        String message = "1010101011100101";

        // Generating RSA Private Key
        BigInteger p = new BigInteger("221671");
        BigInteger q = new BigInteger("228989");
        PrivateKey privateKey = new PrivateKey(p, q);

        // AES Secret Key
        String K = "1100101011110001";

        // Bob sends a packet
        EncryptedTransport sentPacket = new EncryptedTransport(message, K, privateKey);

        // Alice recieves a packet
        DecryptedTransport recievedPacket = new DecryptedTransport(sentPacket);
        // Recieving
        BigInteger z = new BigInteger("" + recievedPacket.getRsaSignature());

        // Testing
        System.out.println(message + " = " + recievedPacket.getDecrypted());
        System.out.println(z + " = " + sentPacket.getHash());
        System.out.println("Are the hashes same? " + recievedPacket.isValid());
    }
}