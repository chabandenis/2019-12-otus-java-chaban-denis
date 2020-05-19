package ru.chaban.cache;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyCacheTest {


    /*
     простой тест, все значения помещаются в кеш и все значения вычитываются
     */
    @Test
    void putMinimum() {
        int cont = 3;
        HWCache<Integer, String> c = new MyCache();

        Stream<Integer> i;

        i = Stream.iterate(1, n -> n + 1).limit(cont);
        i.forEach(x -> {
            System.out.println(x);
            c.put(x, "привет " + x);
        });

        i = Stream.iterate(1, n -> n + 1).limit(cont);

        i.forEach(x -> {
            try {
                assertEquals("привет " + x, c.get(x));
            } catch (NoInCache e) {
                System.out.println(e.getStackTrace());
                assertEquals(true, false);
            }
        });
    }

    @Test
        // попытка поместить больше значений, чем это возможно
    void putMaximum() {
        int cont = 10;
        HWCache<Integer, String> c = new MyCache();

        Stream<Integer> i;

        i = Stream.iterate(1, n -> n + 1).limit(cont);
        i.forEach(x -> {
            c.put(x, "привет " + x);
        });

        int inCache = 0;
        int notInCache = 0;

        for (int pos = 1; pos <= cont; pos++) {

            try {
                assertEquals("привет " + pos, c.get(pos));
                inCache++;
            } catch (NoInCache e) {
                System.out.println(e.getMessage());
                notInCache++;
            }
        }
        assertEquals(3, inCache);
        assertEquals(7, notInCache);
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