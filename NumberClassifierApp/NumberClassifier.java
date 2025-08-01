public class NumberClassifier {

    public String classifyNumbers(int[] numbers) {
        StringBuilder result = new StringBuilder();

        for (int number : numbers) {
            if (number % 2 == 0) {
                result.append(number).append(" is even\n");
            } else {
                result.append(number).append(" is odd\n");
            }
        }

        return result.toString();
    }
}
