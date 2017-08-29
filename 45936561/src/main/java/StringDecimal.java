import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

final class StringDecimal {

    private static final Map<Integer, Integer> charToInt = new ConcurrentHashMap<>();

    static {
        charToInt.put(48, 0);
        charToInt.put(49, 1);
        charToInt.put(50, 2);
        charToInt.put(51, 3);
        charToInt.put(52, 4);
        charToInt.put(53, 5);
        charToInt.put(54, 6);
        charToInt.put(55, 7);
        charToInt.put(56, 8);
        charToInt.put(57, 9);
    }

    private static boolean areEqual(String num1, String num2) {
        int size = Math.min(num1.length(), num2.length()) - 1;

        // 1. Compare first n-1 characters where n is max common length for both strings
        for (int i = 0; i < size; i++) {
            if (num1.charAt(i) != num2.charAt(i)) {
                return false;
            }
        }

        int lastDigitDiff = Math.max(num1.charAt(size), num2.charAt(size)) - Math.min(num1.charAt(size), num2.charAt(size));

        // 2. Check last common digit
        if (lastDigitDiff > 1) {
            return false;
        }

        // 3. If both decimal numbers have same size, they are equal at this moment
        if (num1.length() == num2.length()) {
            return true;
        }

        if (num1.length() > num2.length()) {
            return testRemainingDigits(num1, size);
        }

        return testRemainingDigits(num2, size);
    }

    private static boolean testRemainingDigits(String num, int size) {
        int lastDigitsSum = 0;
        int lastDigit = charToInt.getOrDefault((int) num.charAt(num.length() - 1), 0);

        // 1. Check if last digit is equal to 1
        if (lastDigit > 1) {
            return false;
        }

        // 2. Sum all remaining digits from longer string and accept sum == 1
        for (int i = num.length() - 1; i > size; i--) {
            lastDigitsSum += charToInt.getOrDefault((int) num.charAt(i), 0);
        }

        return lastDigit == 0 && lastDigitsSum == 0 ||
                lastDigit == 1 && lastDigitsSum == 1;
    }

    public static void main(String[] args) {
        List<List<Object>> numbers = Arrays.asList(
                Arrays.asList("1.00", "1.000000", true),
                Arrays.asList("120.0", "121.0", false),
                Arrays.asList("120.0", "120.1", true),
                Arrays.asList("1024.00001", "1024.00000", true),
                Arrays.asList("1024.00002", "1024.00000", false),
                Arrays.asList("1024.00001", "1024.0000", true),
                Arrays.asList("1024.00001", "1024", true),
                Arrays.asList("1024.00010", "1024", false),
                Arrays.asList("1024.00002", "1024", false),
                Arrays.asList("1024.00001", "1025.00001", false)
        );

        for (List<Object> data : numbers) {
            String num1 = (String) data.get(0);
            String num2 = (String) data.get(1);
            boolean expected = (boolean) data.get(2);
            boolean result = areEqual(num1, num2);
            String status = expected == result ? "OK" : "FAILED";

            System.out.println("[" + status + "] " + num1 + " == " + num2 + " ? " + result);
        }
    }
}
