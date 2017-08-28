import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

final class FruitsStream {

    public static void main(String[] args) {

        List<Fruit> basket = Arrays.asList(
                new Fruit("Apple", 3),
                new Fruit("Grape", 2),
                new Fruit("Pear", 2),
                new Fruit("Apple", 1),
                new Fruit("Apple", 1),
                new Fruit("Pear", 1),
                new Fruit("Pear", 1),
                new Fruit("Grape", 1)
        );

        List<Fruit> fruits = basket.stream()
                .sorted(Comparator.comparing(Fruit::getSize))
                .collect(Collectors.toList());

        fruits.forEach(System.out::println);
    }

    static class Fruit {
        private final String name;
        private final int size;

        public Fruit(String name, int size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public int getSize() {
            return size;
        }

        @Override
        public String toString() {
            return "Fruit{" +
                    "name='" + name + '\'' +
                    ", size=" + size +
                    '}';
        }
    }

}
