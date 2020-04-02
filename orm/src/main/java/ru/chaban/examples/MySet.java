package ru.chaban.examples;

import java.util.HashSet;
import java.util.Set;

public class MySet {
    public Set<String> set = new HashSet<>();

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }
}
