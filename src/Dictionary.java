import java.util.*;

/**
 * Created by Evgeniy Golubtsov on 28.01.2018.
 */
public class Dictionary {
    final static String testString = "сапог сарай арбуз болт болт бокс биржа";
    private final Map<String, List<String>> dictionary = new TreeMap<>();

    public Dictionary(String source) {
        Arrays.stream(source.split("\\PL+"))
                .map(String::toLowerCase)
                .distinct()
                .forEach(s -> {
                    String letter = s.substring(0, 1);
                    if (!dictionary.containsKey(letter)) {
                        List<String> list = new ArrayList<>();
                        list.add(s);
                        dictionary.put(letter, list);
                    } else {
                        dictionary.get(letter).add(s);
                    }
                });
    }

    // for quick test
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary(testString);
        System.out.println("Source test string: " + "сапог сарай арбуз болт болт бокс биржа");
        System.out.println("Structure of dictionary: " + dictionary.toString());
        System.out.println("Only sorted groups with several elements: " + dictionary.toStringGroupsWithMultipleElements());
    }

    @Override
    public String toString() {
        return dictionary.toString();
    }

    public String toStringGroupsWithMultipleElements() {
        Map<String, List<String>> result = new TreeMap<>();
        dictionary.forEach((k, v) -> {
            if (v.size() > 1) {
                result.put(k, sortValues(v));
            }
        });
        return result.toString();
    }

    private List<String> sortValues(List<String> source) {
        List<String> result = new ArrayList<>();
        result.addAll(source);
        result.sort((s1, s2) -> {
            if (s1.length() == s2.length()) {
                return s1.compareTo(s2);
            } else {
                return s2.length() - s1.length();
            }

        });
        return result;
    }
}
