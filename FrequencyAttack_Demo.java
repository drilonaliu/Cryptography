import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import RelativeFrequencyAttack.RelativeFrequencies;

public class FrequencyAttack_Demo {
    public static void main(String[] args) {

        String encrypted = textFromFile("EncryptedText.txt");
        RelativeFrequencies rf = new RelativeFrequencies(encrypted);
        String text = rf.attack();

        // System.out.println(text);
        // After printing out the first attack, we know the substitutions that should be
        // made
        Map<String, String> map4 = new LinkedHashMap<>();
        map4.put("G", "Q");
        map4.put("T", "I");
        map4.put("O", "P");
        map4.put("L", "U");
        map4.put("U", "K");
        map4.put("D", "L");
        map4.put("I", "T");
        map4.put("P", "M");
        map4.put("K", "O");
        map4.put("M", "D");
        map4.put("C", "Z");
        map4.put("Y", "C");
        map4.put("Q", "G");
        map4.put("Z", "Y");

        // We get the decrypted text
        String decrypted = RelativeFrequencies.subsituteText(text, map4);
        System.out.println(decrypted);

    }

    public static String textFromFile(String path) {
        String text = "";
        try {
            text = new String(Files.readString(Paths.get(path)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

}
