import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Evgeniy Golubtsov on 29.01.2018.
 */
public class DictionaryTest {
    private final static String testString = "   сапог Сарай арбуз    Болт болт Бокс биржа   ";

//    @Test
//    public void main() throws Exception {
//    }

    @Test
    public void testToString() throws Exception {
        Dictionary dictionary = new Dictionary(testString);
        String expectedString = "{а=[арбуз], б=[болт, бокс, биржа], с=[сапог, сарай]}";
        assertEquals(expectedString, dictionary.toString());
        dictionary = new Dictionary(null);
        expectedString = "{}";
        assertEquals(expectedString, dictionary.toString());
    }

    @Test
    public void testToStringGroupsWithMultipleElements() throws Exception {
        Dictionary dictionary = new Dictionary(testString);
        String expectedString = "{б=[биржа, бокс, болт], с=[сапог, сарай]}";
        assertEquals(expectedString, dictionary.toStringGroupsWithMultipleElements());
    }

}