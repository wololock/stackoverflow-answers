import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

final class ResultSet {
    private String field1;
    private String field2;
    private String field3;
    private String field4;

    public ResultSet(String field1, String field2, String field3, String field4) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
    }

    private static final Map<String, Function<ResultSet, Object>> mappings = new HashMap<String, Function<ResultSet, Object>>() {{
        put("field1", ResultSet::getField1);
        put("field2", ResultSet::getField2);
        put("field3", ResultSet::getField3);
        put("field4", ResultSet::getField4);
    }};

    public List<Object> populateResultList(List<String> list) {
        return list.stream()
                .map(fieldName -> mappings.getOrDefault(fieldName.toLowerCase(), it -> null).apply(this))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    public String getField4() {
        return field4;
    }

    public static void main(String[] args) {
        ResultSet resultSet = new ResultSet("lorem", "ipsum", "dolor", "sit amet");

        System.out.println(resultSet.populateResultList(Arrays.asList("Field1", "Field4", "Field3", "Field2", "Field9")));
    }
}
