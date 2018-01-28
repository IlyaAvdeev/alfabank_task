import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Evgeniy Golubtsov on 27.01.2018.
 */
public class Test {
    private static List<String> list = Stream.of("бык", "ботаник", "бум", "болт", "болт", "бокс", "биржа").collect(Collectors.toList());

    public static void main(String[] args) {
        Main.sortList(list);
    }
}
