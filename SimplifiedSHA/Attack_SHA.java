package SimplifiedSHA;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import binaryCalculation.BinaryArithmetic;

public class Attack_SHA {

    /**
     * Performs a brute force attack for finding the message from the hash value.
     * We know the hash, not the message!
     * 
     * @param hash
     * @return a message M such that H(M) = hash
     */
    public static String sameHash(String hash) {
        boolean stop = false;
        String b = "";
        String hash2 = "";

        // for each binary string of length i
        for (int i = 0; i < 33; i++) {
            if (!stop) {
                for (int j = 0; j < Math.pow(2, i); j++) {
                    if (!stop) {
                        b = Integer.toBinaryString(j);
                        b = format(b, i);
                        hash2 = SHA.hash(b);
                        System.out.println(b + "  hash--> " + hash2);
                        if (hash2.equals(hash)) {
                            System.out.println("Found! :  " + b);
                            stop = true;
                            break;
                        }
                    }

                }

            }
        }
        System.out.println("The method has ended.");
        return b;
    }

    /**
     * Check if there is another message other than A, that has the same hash as A.
     * 
     * @param A message
     * @return A', such that A'!=A and H(A) = H(A')
     */
    public static String collision(String A) {
        String hash1 = SHA.hash(A);
        boolean stop = false;

        String b = "";
        String hash2 = "";

        int i = 16;

        while (!stop) {
            for (int j = 0; j < Math.pow(2, i); j++) {
                if (!stop) {
                    b = Integer.toBinaryString(j);
                    b = format(b, i);
                    hash2 = SHA.hash(b);
                    if (hash2.equals(hash1) && !b.equals(A)) {
                        System.out.println(b + "  hash--> " + hash2);
                        stop = true;
                        break;
                    }
                }

            }
            i++;
        }
        return b;
    }

    /**
     * Performs a birthday attack for the hash function.
     * Tries to find any two different messages that produce the same hash.
     * 
     * @param n
     * @return two different binary strings that produce the same hash
     * 
     */
    public static ArrayList<String> birthday(int n) {
        Map<String, String> m = new LinkedHashMap<>();
        ArrayList<String> duplicate = new ArrayList<>();
        String c = "";
        String hash1 = "";
        String hash2 = "";
        String b = "";
        boolean stop = false;
        String dp = "";

        for (int i = 0; i < (int) Math.pow(2, n); i++) {
            b = Integer.toBinaryString(i);
            b = format(b, n);
            hash2 = SHA.hash(b);
            m.put(b, hash2);
            System.out.println(b + "  hash--> " + hash2);
            if (!checkMap(m)) {
                System.out.println("Two values are found! ");
                dp = b;
                // which are these two values?
                for (String key : m.keySet()) {
                    if (!stop) {
                        hash1 = m.get(key);
                        if (hash1.equals(hash2)) {
                            c = key;
                            stop = true;
                            i = (int) Math.pow(2, n);
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(dp + " has the same hash as " + c);
        System.out.println(dp + " -> " + hash2);
        System.out.println(c + " -> " + hash1);

        duplicate.add(dp);
        duplicate.add(c);
        return duplicate;
    }

    // Have two same hashes been generated?
    private static boolean checkMap(Map<String, String> map) {
        Set<String> set = new LinkedHashSet<>();
        map.forEach((x, y) -> {
            set.add(y);
        });

        if (map.size() == set.size())
            return true;
        return false;
    }

    private static String format(String x, int m) {

        if (x.length() < m) {
            x = BinaryArithmetic.generateZeros(m - x.length()) + x;
        }
        return x;
    }

}
