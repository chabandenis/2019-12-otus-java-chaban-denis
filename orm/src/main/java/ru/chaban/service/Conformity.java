package ru.chaban.service;
import java.util.HashMap;
import java.util.Map;

/*
    Прописано соответствие типов данных у объекта и типа данных в БД
 */
public class Conformity {
    private Map<String, String> conf = new HashMap<>();

    public Conformity() {
        conf.put("String", "char(256");
    }

    public Map getConf() {
        return conf;
    }
}
