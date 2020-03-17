package ru.chaban.examples;

import java.util.HashMap;
import java.util.Map;

public class MyMap {
    public Map<String, String> map = new HashMap<>();

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
