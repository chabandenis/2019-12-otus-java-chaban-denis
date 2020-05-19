package ru.chaban.cache;

public interface HWCache<K, V> {

  void put(K key, V value);

  void remove(K key);

  V get(K key) throws NoInCache;

  void addListener(HwListener<K, V> listener);

  void removeListener(HwListener<K, V> listener);
}
