import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    final static String testString = "сапог сарай арбуз болт болт бокс биржа";
    final static  Map<String, List<String>> dictionary = new TreeMap<>();

    public static void main(String[] args) {
        fillDictionary(testString);
    }

    private static void fillDictionary(String source){
        Arrays.stream(source.split("\\PL+"))
            .map(String::toLowerCase)
            .distinct()
            .peek(s -> System.out.println(s))
            .forEach(s -> {
                String letter = s.substring(0,1);
                if (!dictionary.containsKey(letter)){
                    List<String> list = new ArrayList<>();
                    list.add(s);
                    dictionary.put(letter, list);
                } else {
                    List<String> list = dictionary.get(letter);
                    list.add(s);
                    dictionary.put(letter, list);
                }
            });
        System.out.println(getDictionaryByGroups());
        System.out.println(getGroupsWithMoreOneElement());
    }

    public static void sortList(List<String> list){
        list.sort((s1, s2) -> {
            if (s1.length() == s2.length()){
                return s1.compareTo(s2);
            } else {
                return s2.length() - s1.length();
            }

        });
    }

    private static String getDictionaryByGroups(){
        String result = "[";
        Set<String> letters = dictionary.keySet();
        for (String letter : letters){
            result += letter + "=[";
            List<String> list = dictionary.get(letter);
            for (String values : list){
                result += values + ", ";
            }
            result = result.substring(0, result.length() - 2) + "], ";
        }
        result = result.substring(0, result.length() - 2) + "]";
        return result;
    }

    private static String getGroupsWithMoreOneElement(){
        String result = "[";
        Set<String> letters = dictionary.keySet();
        for (String letter : letters){
            List<String> list = dictionary.get(letter);
            if (list.size() > 1){
                result += letter + "=[";
                for (String values : list){
                    result += values + ", ";
                }
                result = result.substring(0, result.length() - 2) + "], ";
            }
        }
        result = result.substring(0, result.length() - 2) + "]";
        return result;
    }

}
