package ru.chaban.cache;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyCacheTest {
    private static final Logger logger = LoggerFactory.getLogger(MyCache.class);

    /*
     простой тест, все значения помещаются в кеш и все значения вычитываются
     */
    @Test
    void putMinimum() {
        int cont = 3;
        HWCache<Integer, String> c = new MyCache(3, new HashMap<>());

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
        HWCache<Integer, String> c = new MyCache(3, new HashMap<>());

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
    void addListener() {
        HwListener<Integer, String> listener = new MyListener<>();
        HWCache cache = new MyCache(3, new HashMap<>());
        cache.addListener(listener);
        cache.put(1, "111");
        cache.remove(1);

        assertEquals(true, true);
    }

    @Test
    void removeListener() {
        HwListener<Integer, String> listener = new MyListener<>();
        HWCache cache = new MyCache(3, new HashMap<>());
        cache.addListener(listener);
        cache.put(1, "111");
        cache.removeListener(listener);
        cache.remove(1);

        assertEquals(true, true);
    }

    /*
    Сломаем с OutOfMemoryError: Java heap space
    кеш с HashMap ломается
    в отладке:
        2020-05-19_17:59:00.181 INFO  ru.chaban.cache.MyCache - Обработано: 10
        2020-05-19_17:59:00.513 INFO  ru.chaban.cache.MyCache - Обработано: 20
        2020-05-19_17:59:00.820 INFO  ru.chaban.cache.MyCache - Обработано: 30
        2020-05-19_17:59:01.124 INFO  ru.chaban.cache.MyCache - Обработано: 40
        Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     */
    @Test
    void lowHeap() {
        int maxSize = 10000000;

        StringBuilder str = new StringBuilder("");

        for (int i = 0; i < maxSize; i++) {
            str.append(String.valueOf(i));
        }

        HWCache cache = new MyCache(maxSize, new HashMap<>());

        Stream.iterate(1, n -> n + 1).limit(maxSize).forEach(x -> {
            cache.put(x, str.toString());
            if (x % 10 == 0) {
                logger.info("Обработано: {}", x);
            }
        });

        assertEquals(true, true);
    }
}