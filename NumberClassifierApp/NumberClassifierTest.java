import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberClassifierTest {

    @Test
    public void testClassifyNumbers_StatementsCovered() {
        NumberClassifier classifier = new NumberClassifier();
        int[] input = {2, 3};
        String expected = "2 is even\n3 is odd\n";
        String actual = classifier.classifyNumbers(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnlyEvenNumbers() {
        NumberClassifier classifier = new NumberClassifier();
        int[] input = {2, 4, 6};
        String expected = "2 is even\n4 is even\n6 is even\n";
        assertEquals(expected, classifier.classifyNumbers(input));
    }

    @Test
    public void testOnlyOddNumbers() {
        NumberClassifier classifier = new NumberClassifier();
        int[] input = {1, 3, 5};
        String expected = "1 is odd\n3 is odd\n5 is odd\n";
        assertEquals(expected, classifier.classifyNumbers(input));
    }
}
