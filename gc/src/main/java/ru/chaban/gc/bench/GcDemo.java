package ru.chaban.gc.bench;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.MBeanServer;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;


/*
Замеры производительности выполнялись для 4 сборщиков мусора и двух объемов кучи для 256М и 4096М.
Выполнялись на ноутбуке i7 8850U 1.8GHz. 12ГБ ОЗУ

Выводы.
По субьективным ощущением есть ошибка в выодах, ожидал, что g1 будет номером 1, но оказалось, что это не так.
Наиболее эффективен последовательный сборщик мусора программа отработала за 112с.
Рейтинг сборщиков мусора отсортированный по времени выполнения программы.
-XX:+UseSerialGC 		    duration:112
-XX:+UseG1GC 			    duration:149
-XX:+UseConcMarkSweepGC		duration:190
-XX:+UseParallelGC 		    duration:366;

На слабых компютерах SerialGC должен быть эффективным, так как не тратит ресурсов процессора. Здесь же памяти было мало,
вероятно многопоточная обработка сильно усложняла процесс сборки.

Если взять второй праметр с общим временем останоки программы, то побеждает последоваетльный сборщик мусора,
его время минимально.
-XX:+UseSerialGC            stop the world:97.583
-XX:+UseConcMarkSweepGC     stop the world:102.043
-XX:+UseG1GC                stop the world:108.05;
-XX:+UseParallelGC          stop the world:348.834

Скорее всего так получилось из за того, что памяти мало и никто не конкурировал за ее очистку друг с другом.

Оценка по среднему времени остановки программы выигрывает G1, у него время работы самое минимальное.
-XX:+UseG1GC                avg stop: 0,026;     duration:149; stop the world:108.05; counts = 4136
-XX:+UseConcMarkSweepGC:    avg stop: 0,051;     duration:190; stop the word:102.043; counts = 1989
-XX:+UseSerialGC            avg stop: 0,065;     duration:112; stop the world:97.583; counts = 1498
-XX:+UseParallelGC          avg stop: 0,139;     duration:366; stop the world:348.834; counts = 2494

Изменение среднего времени простоя программы в два раза с 0,065 с до 0,026
привело к увеличению времени работы в 1.3 раза с 112с до 149с

Увеличение памяти на порядок изменило время работы, но SerialGC оказался самым быстрым
===========================================================
-XX:+UseConcMarkSweepGC
-Xms256m
-Xmx256m

duration:190; stop the world:102.043; counts = 1989

-XX:+UseConcMarkSweepGC
-Xms4096m
-Xmx4096m
duration:28; stop the world:13.467; counts = 126
===========================================================
-XX:+UseG1GC
-Xms256m
-Xmx256m
duration:149; stop the world:108.05; counts = 4136

-XX:+UseG1GC
-Xms4096m
-Xmx4096m
duration:80; stop the world:39.415; counts = 241

===========================================================
-XX:+UseSerialGC
-Xms256m
-Xmx256m
duration:112; stop the world:97.583; counts = 1498

-XX:+UseSerialGC
-Xms4096m
-Xmx4096m
duration:17; stop the world:0.5; counts = 62
===========================================================
-XX:+UseParallelGC
-Xms256m
-Xmx256m
duration:366; stop the world:348.834; counts = 2494

-XX:+UseParallelGC
-Xms4096m
-Xmx4096m
duration:19; stop the world:2.574; counts = 65

===========================================================
*/

public class GcDemo {
    static public double allStopTime = 0;
    static public int times = 0;

    public static void main(String... args) throws Exception {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        switchOnMonitoring();
        long beginTime = System.currentTimeMillis();

        int size = 5 * 1000 * 1000;
        int loopCounter = 500;  //1000
        //int loopCounter = 100000;
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.otus:type=Benchmark");

        Benchmark mbean = new Benchmark(loopCounter);
        mbs.registerMBean(mbean, name);
        mbean.setSize(size);
        mbean.run();

        System.out.println("duration:" + (System.currentTimeMillis() - beginTime) / 1000 + "; stop the world:" + allStopTime / 1000 + "; counts = " + times);
    }

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

}
