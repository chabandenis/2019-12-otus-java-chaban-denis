package ru.chaban.service;

import java.util.HashMap;
import java.util.Map;

public class Field {
    private Map<String, String> value = new HashMap<>();

    public Map<String, String> getValue() {
        return value;
    }

    public void setValue(Map<String, String> value) {
        this.value = value;
    }
}
