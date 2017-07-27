import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Main {

    public static void main(String args[]) {
        final List<String> animals = new ArrayList<>();
        animals.add("cat");
        animals.add("dog");
        animals.add("donkey");

        final Map<String, List<String>> houses = new ConcurrentHashMap<>();
        houses.put("cat", Arrays.asList("white house", "black house"));
        houses.put("dog", Arrays.asList("blue house"));
        houses.put("donkey", Arrays.asList("black house"));

        final Map<String, List<String>> planets = new ConcurrentHashMap<>();
        planets.put("white house", Arrays.asList("earth", "mars"));
        planets.put("green house", Arrays.asList("earth", "jupiter"));
        planets.put("blue house", Arrays.asList("jupiter", "mars"));
        planets.put("black house", Arrays.asList("mars"));

        final List<Animal> zoo = animals.parallelStream()
                .map(animal -> houses.getOrDefault(animal, Collections.emptyList())
                        .parallelStream()
                        .map(house -> planets.getOrDefault(house, Collections.emptyList())
                                .parallelStream()
                                .map(planet -> new Animal(animal, house, planet))
                                .collect(Collectors.toList())
                        )
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
                )
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        zoo.forEach(System.out::println);
    }

    static final class Animal {
        private final String name;
        private final String house;
        private final String planet;

        Animal(String name, String house, String planet) {
            this.name = name;
            this.house = house;
            this.planet = planet;
        }

        @Override
        public String toString() {
            return "Animal{" +
                    "name='" + name + '\'' +
                    ", house='" + house + '\'' +
                    ", planet='" + planet + '\'' +
                    '}';
        }
    }
}
