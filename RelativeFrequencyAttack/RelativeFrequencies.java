package RelativeFrequencyAttack;

import java.util.*;

public class RelativeFrequencies {

    private String text;
    private String[] alphabet = toArray("ABCDEËFGHIJKLMNOPQRSTUVWXYZ"); // Albanian alphabet
    private String[] frequencies = toArray("EËTIARNSHUKOMDJPLGVBQFZYCÇX"); // frequency distribution

    // private Map<String, String> map = new LinkedHashMap<>();

    public RelativeFrequencies(String text) {
        this.text = text;
    }

    /**
     * Performs a relative frequency attack on a text
     * 
     * @return substituted text with relative frequencies
     */
    public String attack() {
        return subsituteText(text, mapRule(sortMap(textFrequencies())));
    }

    /**
     * Subsitutes each letter with another based on the mapping rule
     */
    public static String subsituteText(String text, Map<String, String> map3) {
        String text2 = "";
        char z;
        for (int i = 0; i < text.length(); i++) {
            z = text.charAt(i);
            try {
                text2 += map3.get(String.valueOf(z)).charAt(0);
            } catch (java.lang.NullPointerException e) {
                text2 += String.valueOf(z);
            }
        }
        return text2;
    }

    /**
     * Creates mapping rule from current text frequencies with frequency
     * distribution.
     * If "t" is the most repeatd letter in the text then "t" will be mapped to
     * "e".
     */
    private Map<String, String> mapRule(Map<Integer, String> map2) {
        Map<String, String> map3 = new LinkedHashMap<>();
        ArrayList<String> placeholder = new ArrayList<String>(map2.values());
        for (int i = 0; i < placeholder.size(); i++) {
            map3.put(placeholder.get(i), frequencies[i]);
        }
        return map3;
    }

    /**
     * @return each letter how many times is repeated in the encrypted text
     */
    private Map<String, Integer> textFrequencies() {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < alphabet.length; i++) {
            map.put(alphabet[i], Integer.valueOf(0));
        }
        // iterate in the text
        for (int i = 0; i < text.length(); i++) {
            try {
                char z = text.charAt(i);
                map.put(String.valueOf(z), map.get(String.valueOf(z)) + 1);
            } catch (java.lang.NullPointerException e) {
                // avoiding spaces, dots
            }
        }
        return map;
    }

    /**
     * Sort map starting by the most repeated letter to the least repeated letter.
     */
    private Map<Integer, String> sortMap(Map<String, Integer> map) {
        Map<Integer, String> map2 = new TreeMap<>(
                (Comparator<Integer>) (o1, o2) -> o2.compareTo(o1));

        map.forEach((a, b) -> {
            map2.put(b, a);
        });

        return map2;
    }

    private String[] toArray(String word) {
        String[] a = new String[word.length()];
        char p;
        for (int i = 0; i < word.length(); i++) {
            p = word.charAt(i);
            a[i] = String.valueOf(p);
        }
        return a;
    }

}
