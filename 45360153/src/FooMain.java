import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FooMain {

    public static void main(String[] args) {
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        final List<Foo> foos = Arrays.asList(
                new Foo("Test", "Test", LocalDate.parse("10/02/2015", dateFormat), 5),
                new Foo("Test", "Test", LocalDate.parse("10/02/2015", dateFormat), 4),
                new Foo("Test", "Test", LocalDate.parse("10/02/2015", dateFormat), 3),
                new Foo("Test", "Test", LocalDate.parse("02/02/2015", dateFormat), 5),
                new Foo("Test", "Potato", LocalDate.parse("02/02/2015", dateFormat), 5)
        );

        List<FooResult> result = foos.stream()
                .collect(Collectors.groupingBy(foo -> new MapKey(foo.a, foo.b, foo.c), Collectors.mapping(Foo::getD, Collectors.toList())))
                .entrySet()
                .stream()
                .map(entry -> new FooResult(entry.getKey().a, entry.getKey().b, entry.getKey().c, entry.getValue()))
                .collect(Collectors.toList());

        result.forEach(System.out::println);
    }

    public static class Foo {
        private final String a;
        private final String b;
        private final LocalDate c;
        private final int d;

        Foo(String a, String b, LocalDate c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        int getD() {
            return d;
        }
    }

    public static class FooResult {
        private final String a;
        private final String b;
        private final LocalDate c;
        private final List<Integer> d;

        FooResult(String a, String b, LocalDate c, List<Integer> d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Override
        public String toString() {
            return "FooResult{" +
                    "a='" + a + '\'' +
                    ", b='" + b + '\'' +
                    ", c=" + c +
                    ", d=" + d +
                    '}';
        }
    }

    public static class MapKey {
        private final String a;
        private final String b;
        private final LocalDate c;

        MapKey(String a, String b, LocalDate c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MapKey)) return false;

            MapKey mapKey = (MapKey) o;

            if (a != null ? !a.equals(mapKey.a) : mapKey.a != null) return false;
            if (b != null ? !b.equals(mapKey.b) : mapKey.b != null) return false;
            return c != null ? c.equals(mapKey.c) : mapKey.c == null;
        }

        @Override
        public int hashCode() {
            int result = a != null ? a.hashCode() : 0;
            result = 31 * result + (b != null ? b.hashCode() : 0);
            result = 31 * result + (c != null ? c.hashCode() : 0);
            return result;
        }
    }
}
