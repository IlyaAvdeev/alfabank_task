import java.util.*;

/**
 * Разработана по тестовому заданию ""Альфа-Банк.
 *
 * @author Голубцов Евгений
 * @version 1.0
 * @link https://github.com/golubtsoff/alfabank_task
 * <p>
 * Задание:
 * Есть строка, состоящая из слов. Все слова в ней разделены одним пробелом.
 * Нужно преобразовать строку в такую структуру данных, которая группирует слова по первой букве в слове.
 * Затем вывести только группы, содержащие более одного элемента.
 * Группы должны быть отсортированы в алфавитном порядке по всем буквам в слове.
 * Слова внутри группы нужно сортировать по убыванию (по количеству символов);
 * если число символов равное, то сортировать в алфавитном порядке.
 * <p>
 * Пример строки: String s = "сапог сарай арбуз болт бокс биржа"
 * Отсортированная строка: [б=[биржа, бокс, болт], c=[caпог, сарай]]
 */
public class Dictionary {
    private static final String KEY_LAUNCH_CONSOLE = "-c";
    private final static String testString = "   сапог Сарай арбуз    Болт болт Бокс биржа   ";
    public String sourceString;
    private final Map<String, List<String>> dictionary = new TreeMap<>();

    public Dictionary(String source) {
        sourceString = source;
        if (source == null || source.isEmpty()) return;
        Arrays.stream(source.trim().split("\\PL+"))
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
        Dictionary dictionary;
        if (args.length == 1 && args[0].equalsIgnoreCase(KEY_LAUNCH_CONSOLE)) {
            System.out.println("Enter some words and press enter:");
            Scanner in = new Scanner(System.in);
            dictionary = new Dictionary(in.nextLine());
        } else {
            System.out.println("This is the demonstration mode...");
            dictionary = new Dictionary(testString);
        }
        System.out.println("Source test string: " + dictionary.sourceString);
        System.out.println("Structure of dictionary: " + dictionary.toString());
        System.out.println("Only sorted groups with several elements: " + dictionary.toStringGroupsWithMultipleElements());
    }

    /**
     * Метод формирования структуры словаря в строку
     * Выводятся все имеющиеся группы
     *
     * @return Возвращает строку с записанными группами
     */
    @Override
    public String toString() {
        return dictionary.toString();
    }

    /**
     * Метод формирования структуры словаря в строку
     * Выводятся только группы, имеющие больше одного элемента
     *
     * @return Возвращает строку с записанными группами
     */
    public String toStringGroupsWithMultipleElements() {
        Map<String, List<String>> result = new TreeMap<>();
        dictionary.forEach((k, v) -> {
            if (v.size() > 1) {
                result.put(k, sortValues(v));
            }
        });
        return result.toString();
    }

    /**
     * Метод сортировки данных
     *
     * @param source Список с данными, подлежащий сортировке
     * @return Возвращает список отсортированных данных
     */
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