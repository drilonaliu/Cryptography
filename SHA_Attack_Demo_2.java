import SimplifiedSHA.*;

public class SHA_Attack_Demo_2 {

    public static void main(String[] args) {

        /*
         * Perform a birthday attack on Simplified SHA1 to find any two messages that
         * produce the same hash
         */

        Attack_SHA.birthday(16);

        // After the execution of the attack two same values were found!
        // 0011000111011101 -> C8A2192F and 0000010010001101 -> C8A2192F

    }
}
