package com.github.wololock;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReflectionTest {

    private final List<Class<? extends Throwable>> list = new LinkedList<Class<? extends Throwable>>() {{
        add(RuntimeException.class);
        add(IllegalArgumentException.class);
    }};

    @Test
    public void test() {
        assertThat(findMostSpecific(IllegalArgumentException.class)).isEqualTo(IllegalArgumentException.class);

        assertThat(findMostSpecific(RuntimeException.class)).isEqualTo(RuntimeException.class);

        assertThat(findMostSpecific(IllegalStateException.class)).isEqualTo(RuntimeException.class);

        assertThat(findMostSpecific(IllegalStateException.class)).isEqualTo(RuntimeException.class);

        assertThat(findMostSpecific(CustomException.class)).isEqualTo(IllegalArgumentException.class);
    }

    public Class<? extends Throwable> findMostSpecific(Class<? extends Throwable> type) {
        return list.stream()
                .filter(e -> type.getCanonicalName().equals(e.getCanonicalName()))
                .findAny()
                .orElseGet(() -> findMostSpecific((Class<? extends Throwable>) type.getSuperclass()));
    }

    public static class CustomException extends IllegalArgumentException {}
}
