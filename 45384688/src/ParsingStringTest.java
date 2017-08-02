import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParsingStringTest {

    public static void main(String[] args) {
        String str = "1, 1&0 , 3, 4, 5, , 7, sasd, aaa, 0";

        List<Double> doubles = parse(str, s -> Double.valueOf(s.replaceAll("&", ".")));

        System.out.println(doubles);  // Prints: [1.0, 1.0, 3.0, 4.0, 5.0, 7.0, 0.0]
    }

    public static <T> List<T> parse(String str, Function<String, T> parseFunction) {
        return Arrays.stream(str.split(","))
                .filter(s -> !s.isEmpty())
                .map(s -> {
                    try {
                        return parseFunction.apply(s.trim());
                    } catch (Exception e) {
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
