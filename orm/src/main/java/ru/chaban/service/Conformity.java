/*
    Прописано соответствие типов данных у объекта и типа данных в БД
 */

package ru.chaban.service;

import java.util.HashMap;
import java.util.Map;

public class Conformity {
    private Map<String, String> conf = new HashMap<>();

    public Conformity() {
        conf.put("String", "char(256");
    }

    public Map getConf() {
        return conf;
    }
}
