import SimplifiedSHA.Attack_SHA;

public class SHA_Attack_Demo_3 {
    public static void main(String[] args) {

        /*
         * Is there any binary string other than M = 0000010010001101, that has the same
         * hash as M, H(M) = C8A2192F ?
         */
        //

        Attack_SHA.collision("0000010010001101");

        // Found other binary string souch that 0011000111011101 hash--> C8A2192F

        /*
         * NOTE
         * So that the attack can be successful, the message M=0000010010001101 was
         * purposely chosen because from Demo_2 we
         * found out that there is another M' such that H(M)=H(M') and M=M'.
         */
    }
}
