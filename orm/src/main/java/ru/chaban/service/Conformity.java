package ru.chaban.service;

import java.util.HashMap;
import java.util.Map;

/*
    Прописано соответствие типов данных у объекта и типа данных в БД
 */
public class Conformity {
    private Map<String, String> conf = new HashMap<>();

    public Conformity() {
        conf.put("class java.lang.String", "char(256)".toUpperCase());
        conf.put("class java.lang.Long", "number".toUpperCase());
        conf.put("class java.lang.Integer", "number".toUpperCase());
        conf.put("int", "number".toUpperCase());
        conf.put("long", "number".toUpperCase());
        conf.put("class java.lang.Double", "number".toUpperCase());
    }

    public String get(String from) {
        return conf.get(from);
    }
}
