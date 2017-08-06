import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ListOfObjectsToListOfMapsTest {

    @Test
    public void testTransformingListOfObjectsIntoListOfMaps() {
        //given:
        final List<Div> divs = Arrays.asList(
                new Div("1", "A"),
                new Div("2", "B")
        );

        //when:
        final List<Map<String, String>> result = divs.stream()
                .reduce(new ArrayList<>(), (list, div) -> {
                    list.add(new HashMap<String, String>() {{
                        put("id", div.getId());
                        put("name", div.getName());
                    }});
                    return list;
                }, (a, b) -> a);

        //then:
        assertThat(result).hasSize(2);
        //and:
        assertThat(result.get(0)).isEqualTo(new HashMap<String, String>() {{
            put("id", "1");
            put("name", "A");
        }});
        //and:
        assertThat(result.get(1)).isEqualTo(new HashMap<String, String>() {{
            put("id", "2");
            put("name", "B");
        }});
    }

    static class Div {
        private final String id;
        private final String name;

        public Div(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Div{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
