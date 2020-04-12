package ru.chaban.service;

import java.util.HashMap;
import java.util.Map;

/*
    Прописано соответствие типов данных у объекта и типа данных в БД
 */
public class Conformity {
    private Map<String, String> conf = new HashMap<>();

    public Conformity() {

        conf.put("class java.lang.String", "char(256)");
        conf.put("class java.lang.Long", "number");
        conf.put("class java.lang.Integer", "number");
        conf.put("int", "number");
        conf.put("long", "number");

    }

    public String get(String from) {
        return conf.get(from);
    }
}
