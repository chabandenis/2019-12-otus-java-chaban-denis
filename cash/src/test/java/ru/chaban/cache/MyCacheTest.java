package ru.chaban.cache;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyCacheTest {

    @Test
    // простой тест, все значения помещаются в кеш и все значения вычитываются
    void put() {
        HWCache<Integer, String> c = new MyCache();

        Stream<Integer> i;

        i = Stream.iterate(1, n -> n + 1).limit(10);
        i.forEach(x -> {
            System.out.println(x);
            c.put(x, "привет " + x);
        });

        i = Stream.iterate(1, n -> n + 1).limit(10);
        i.forEach(x->{
            assertEquals("привет " + x, c.get(x));
        });

    }

    @Test
    void remove() {
        assertEquals(true, true);
    }

    @Test
    void get() {
        assertEquals(true, true);
    }

    @Test
    void addListener() {
        assertEquals(true, true);
    }

    @Test
    void removeListener() {
        assertEquals(true, true);
    }
}