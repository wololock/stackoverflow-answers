package com.github.wololock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CompactComaDelimitedNumbersTest {

    @Test
    public void testCompactingNumbersWithJavaStream() {
        //given:
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 12, 13, 14, 19);

        //when:
        final List<Object> finalResult = list.stream()
                // Firstly let's pair every number with a number it starts from in
                // given sequence
                .reduce(new ArrayList<Pair<Integer, Integer>>(), (result, number) -> {
                    if (result.isEmpty()) {
                        result.add(new Pair<>(number, number));
                        return result;
                    }

                    final Pair<Integer, Integer> previous = result.get(result.size() - 1);
                    if (previous.getFirst() + 1 == number) {
                        result.add(new Pair<>(number, previous.getSecond()));
                    } else {
                        result.add(new Pair<>(number, number));
                    }
                    return result;
                }, (a, b) -> a)
                // Now let's group list of pair into a Map where key is a number 'from' and value is a list of values
                // in given sequence starting from 'from' number
                .stream()
                .collect(Collectors.groupingBy(Pair::getSecond, Collectors.mapping(Pair::getFirst, Collectors.toList())))
                // Finally let's sort entry set and convert into expected format
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(e -> e.getValue().size() < 3 ?
                        e.getValue() :
                        Collections.singletonList(String.format("%d-%d", e.getValue().get(0), e.getValue().get(e.getValue().size() - 1))))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        //then:
        assertThat(finalResult, is(equalTo(Arrays.asList("1-5", "12-14", 19))));

    }

    static final class Pair<T,K> {
        private final T first;
        private final K second;
        Pair(T first, K second) {
            this.first = first;
            this.second = second;
        }
        public T getFirst() {
            return first;
        }

        public K getSecond() {
            return second;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }
    }
}
