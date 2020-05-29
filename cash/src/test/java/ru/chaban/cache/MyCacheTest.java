package ru.chaban.cache;

import com.sun.management.GarbageCollectionNotificationInfo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    прошу прочитать
 */
class MyCacheTest {
    private static final Logger logger = LoggerFactory.getLogger(MyCache.class);

    static public double allStopTime = 0;
    static public int times = 0;

    private static void switchOnMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            System.out.println("GC name:" + gcbean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    String gcName = info.getGcName();
                    String gcAction = info.getGcAction();
                    String gcCause = info.getGcCause();

                    long startTime = info.getGcInfo().getStartTime();
                    long duration = info.getGcInfo().getDuration();
                    allStopTime += duration;
                    times++;

                    System.out.println("start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)");
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }

    /*
     простой тест, все значения помещаются в кеш и вычитываются
     отладка:
        2020-05-20_17:08:22.571 INFO  ru.chaban.cache.MyListener - key:1, value:привет 1, action: добавили
        2020-05-20_17:08:22.574 INFO  ru.chaban.cache.MyListener - key:2, value:привет 2, action: добавили
        2020-05-20_17:08:22.574 INFO  ru.chaban.cache.MyListener - key:3, value:привет 3, action: добавили
     */
    @Test
    void putMinimum() {
        int cont = 3;
        HWCache<Integer, String> cache = new MyCache(3, new HashMap<>());

        HwListener<Integer, String> listener = new MyListener<>();
        cache.addListener(listener);

        Stream<Integer> i;

        i = Stream.iterate(1, n -> n + 1).limit(cont);
        i.forEach(x -> {
            cache.put(x, "привет " + x);
        });

        i = Stream.iterate(1, n -> n + 1).limit(cont);

        i.forEach(x -> {
            try {
                assertEquals("привет " + x, cache.get(x));
            } catch (NoInCache e) {
                System.out.println(e.getStackTrace());
                assertEquals(true, false);
            }
        });
    }

    /*
        Помещаю и читаю больше значений, чем это возможно

        Отладка:
            2020-05-20_17:10:47.293 INFO  ru.chaban.cache.MyListener - key:1, value:привет 1, action: добавили
            2020-05-20_17:10:47.296 INFO  ru.chaban.cache.MyListener - key:2, value:привет 2, action: добавили
            2020-05-20_17:10:47.296 INFO  ru.chaban.cache.MyListener - key:3, value:привет 3, action: добавили
            2020-05-20_17:10:47.297 INFO  ru.chaban.cache.MyListener - key:1, value:привет 1, action: удалили
            2020-05-20_17:10:47.297 INFO  ru.chaban.cache.MyListener - key:4, value:привет 4, action: добавили
            2020-05-20_17:10:47.297 INFO  ru.chaban.cache.MyListener - key:2, value:привет 2, action: удалили
            2020-05-20_17:10:47.297 INFO  ru.chaban.cache.MyListener - key:5, value:привет 5, action: добавили
            2020-05-20_17:10:47.297 INFO  ru.chaban.cache.MyListener - key:3, value:привет 3, action: удалили
            2020-05-20_17:10:47.298 INFO  ru.chaban.cache.MyListener - key:6, value:привет 6, action: добавили
            2020-05-20_17:10:47.298 INFO  ru.chaban.cache.MyListener - key:4, value:привет 4, action: удалили
            2020-05-20_17:10:47.298 INFO  ru.chaban.cache.MyListener - key:7, value:привет 7, action: добавили
            2020-05-20_17:10:47.298 INFO  ru.chaban.cache.MyListener - key:5, value:привет 5, action: удалили
            2020-05-20_17:10:47.298 INFO  ru.chaban.cache.MyListener - key:8, value:привет 8, action: добавили
            2020-05-20_17:10:47.298 INFO  ru.chaban.cache.MyListener - key:6, value:привет 6, action: удалили
            2020-05-20_17:10:47.298 INFO  ru.chaban.cache.MyListener - key:9, value:привет 9, action: добавили
            2020-05-20_17:10:47.298 INFO  ru.chaban.cache.MyListener - key:7, value:привет 7, action: удалили
            2020-05-20_17:10:47.299 INFO  ru.chaban.cache.MyListener - key:10, value:привет 10, action: добавили
            для элемента с ключом 1 отсутствует значение
            для элемента с ключом 2 отсутствует значение
            для элемента с ключом 3 отсутствует значение
            для элемента с ключом 4 отсутствует значение
            для элемента с ключом 5 отсутствует значение
            для элемента с ключом 6 отсутствует значение
            для элемента с ключом 7 отсутствует значение
     */
    @Test
    void putMaximum() {
        int cont = 10;
        HWCache<Integer, String> cache = new MyCache(3, new HashMap<>());

        HwListener<Integer, String> listener = new MyListener<>();
        cache.addListener(listener);

        Stream<Integer> i;

        i = Stream.iterate(1, n -> n + 1).limit(cont);
        i.forEach(x -> {
            cache.put(x, "привет " + x);
        });

        int inCache = 0;
        int notInCache = 0;

        for (int pos = 1; pos <= cont; pos++) {

            try {
                assertEquals("привет " + pos, cache.get(pos));
                inCache++;
            } catch (NoInCache e) {
                System.out.println(e.getMessage());
                notInCache++;
            }
        }
        assertEquals(3, inCache);
        assertEquals(7, notInCache);
    }

    /*
        Добавление подписчика
        Отладка:
            2020-05-20_17:14:18.513 INFO  ru.chaban.cache.MyListener - key:1, value:111, action: добавили
            2020-05-20_17:14:18.517 INFO  ru.chaban.cache.MyListener - key:1, value:111, action: удалили
     */
    @Test
    void addListener() {
        HwListener<Integer, String> listener = new MyListener<>();
        HWCache cache = new MyCache(3, new HashMap<>());
        cache.addListener(listener);
        cache.put(1, "111");
        cache.remove(1);

        assertEquals(true, true);
    }

    /*
        Добавление и удаление подписчика
        Отладка:
            2020-05-20_17:16:05.771 INFO  ru.chaban.cache.MyListener - key:1, value:111, action: добавили
     */
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
    Ломается. Так задумано!!! OutOfMemoryError: Java heap space. (тесты приходится запускать вручную)
    кэш HashMap
    в отладке:
    2020-05-20_18:12:53.836 INFO  ru.chaban.cache.MyCache - Обработано: 100
    2020-05-20_18:12:54.652 INFO  ru.chaban.cache.MyCache - Обработано: 200
    Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     */
    @Test
    void lowHeap() {
        int maxSize = 2000000;

        StringBuilder str = new StringBuilder("");

        for (int i = 0; i < maxSize; i++) {
            str.append(String.valueOf(i));
        }

        HWCache cache = new MyCache(maxSize, new HashMap<>());

        Stream.iterate(1, n -> n + 1).limit(maxSize).forEach(x -> {
            cache.put(x, str.toString());
            if (x % 100 == 0) {
                logger.info("Обработано: {}", x);
            }
        });

        assertEquals(true, true);
    }

    /*
    Тест аналогичен предыдущему, но заменил HashMap на weakHashMap
    пример работает, вызывать gc не нужно, он сам успевает
    добавлена информация из GC
    работает долго, не дожидАлся окончания работы
    В отладке видно, что размер кэша изменяется. Видно, что weakHashMap работает и удаляет данные.
    2020-05-21_09:41:53.919 INFO  ru.chaban.cache.MyCache - Обработано: 2400. Размер кэша: 128
    2020-05-21_09:41:54.260 INFO  ru.chaban.cache.MyCache - Обработано: 2500. Размер кэша: 131
    2020-05-21_09:42:04.070 INFO  ru.chaban.cache.MyCache - Обработано: 5400. Размер кэша: 129
    2020-05-21_09:43:04.793 INFO  ru.chaban.cache.MyCache - Обработано: 24000. Размер кэша: 128
     */
    @Test
    void weakHashMapWithGc() {
        switchOnMonitoring();
        int maxSize = 2000000;

        StringBuilder str = new StringBuilder("");

        for (int i = 0; i < maxSize; i++) {
            str.append(String.valueOf(i));
        }

        Map weak = new WeakHashMap<>();

        HWCache cache = new MyCache(maxSize, weak);

        Stream.iterate(1, n -> n + 1).limit(maxSize).forEach(x -> {
            //System.gc(); не нужен, срабатывает автоматом
            cache.put(x, str.toString());
            if (x % 100 == 0) {
                logger.info("Обработано: {}. Размер кэша: {}. Количество ключей: {}",
                        x, weak.size(), weak.keySet().size());
            }
        });

        assertEquals(true, true);
    }

}