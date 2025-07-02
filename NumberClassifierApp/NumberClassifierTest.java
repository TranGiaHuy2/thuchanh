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
}
