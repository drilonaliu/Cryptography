import SimplifiedSHA.*;
import binaryCalculation.BinaryArithmetic;

public class SHA_Attack_Demo_1 {
    public static void main(String[] args) {

        // Given H(M) = 4BAFE69C, where H(M) is function, find M' such that H(M') = H(M)
        String hash = "4BAFE69C";
        String N = Attack_SHA.sameHash(hash); // a brute force attack

        // When N is found, print it's hash
        System.out.println(SHA.hash(BinaryArithmetic.toBinary(N)));
    }
}
