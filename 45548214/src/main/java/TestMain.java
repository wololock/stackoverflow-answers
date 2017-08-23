import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class TestMain {

    public static void main(String[] args) {
        final List<String> oldList = Arrays.asList("asd-qwe", "zxc", "123");
        final Map<String, String> map = new HashMap<String, String>() {{
            put("asd", "zcx");
            put("12", "09");
            put("qq", "aa");
        }};

        List<String> result = oldList.stream()
                .map(line -> map.entrySet()
                        .stream()
                        .filter(entry -> line.startsWith(entry.getKey()))
                        .map(entry -> line.replace(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList())
                )
                .flatMap(Collection::stream)
                .collect(Collectors.toList());


        System.out.println(result);
    }
}
