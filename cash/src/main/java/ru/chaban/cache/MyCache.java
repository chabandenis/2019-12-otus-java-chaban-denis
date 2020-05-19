package ru.chaban.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCache<K, V> implements HWCache<K, V> {
    private static final Logger logger = LoggerFactory.getLogger(MyCache.class);
    // подписчики
    List<HwListener> myListener = new ArrayList<>();
    // максимальное количество элементов в кеше
    private int maxCount = 3;
    // кеш
    Map<K, V> cache = new HashMap<>(maxCount);

    @Override
    public void put(K key, V value) {
        if (cache.size() >= maxCount) {
            for (K k : cache.keySet()) {
                logger.info("Удалилим: {} ", k);
                remove(k);
                break;
            }
        }

        logger.info("Добавили: {} /\"{}\"", key, value);
        cache.put(key, value);
        myListener.stream().forEach(x -> x.notify(key, cache.get(key), "добавили"));
    }

    @Override
    public void remove(K key) {
        if (cache.containsKey(key)) {
            myListener.stream().forEach(x -> x.notify(key, cache.get(key), "удалили"));
            cache.remove(key);
        }
    }

    @Override
    public V get(K key) throws NoInCache {
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        throw new NoInCache("для элемента с ключом " + key + " отсутствует значение");
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        if (!myListener.contains(listener)) {
            myListener.add(listener);
        }
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        if (myListener.contains(listener)) {
            myListener.remove(listener);
        }
    }
}
