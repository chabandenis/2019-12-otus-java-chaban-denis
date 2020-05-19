package ru.chaban.cache;

import java.util.HashMap;
import java.util.Map;

public class MyCache<K, V> implements HWCache<K, V> {

    // максимальное количество элементов в кеше
    private int maxCount = 3;

    // сохраненные значения
    Map<K, V> cache = new HashMap<>(maxCount);

    @Override
    public void put(K key, V value) {
        if (cache.size() >= maxCount) {
            for (K k : cache.keySet()) {
                System.out.println("Удалилим " + k);
                remove(k);
                break;
            }
        }

        System.out.println("Добавили: " + key + "/\"" + value + "\"");
        cache.put(key, value);
    }

    @Override
    public void remove(K key) {
        if(cache.containsKey(key)){
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

    }

    @Override
    public void removeListener(HwListener<K, V> listener) {

    }
}
