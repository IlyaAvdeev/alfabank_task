import java.util.*;

public class Main {
    public static void main(String[] args) {
        String s = "сапог сарай арбуз болт бокс биржа";
        var result = new HashMap<Character, List<String>>();
        Arrays.stream(s.split("\s")).forEach(item -> fun(result, item));
        result.entrySet().stream()
                .filter(item -> item.getValue().size() > 1)
                .sorted(new Comparator<Map.Entry<Character, List<String>>>() {
            @Override
            public int compare(Map.Entry<Character, List<String>> o1, Map.Entry<Character, List<String>> o2) {
                if (o1.getKey() < o2.getKey()) {
                    return -1;
                }
                else if (o1.getKey() > o2.getKey()) {
                    return 1;
                } else
                    return 0;
            }
        }).forEach(item -> {
            Collections.sort(item.getValue());
            System.out.println(item);
        });
    }

    static Map<Character, List<String>> fun(Map<Character, List<String>> col, String w) {
        Character l =  w.charAt(0);
        if (col.containsKey(l)) {
            col.get(l).add(w);
        } else {
            var ar = new ArrayList<String>();
            ar.add(w);
            col.put(l, ar);
        }
        return col;
    }
}
