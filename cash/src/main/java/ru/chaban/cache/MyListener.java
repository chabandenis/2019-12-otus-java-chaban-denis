package ru.chaban.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyListener<K, V> implements HwListener<K, V> {
    private static final Logger logger = LoggerFactory.getLogger(MyListener.class);

    public void notify(K key, V value, String action) {
        logger.info("key:{}, value:{}, action: {}", key, value, action);
    }

    ;
}
