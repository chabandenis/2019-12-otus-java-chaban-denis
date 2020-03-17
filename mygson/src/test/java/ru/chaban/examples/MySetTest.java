package ru.chaban.examples;

import org.junit.jupiter.api.Test;
import ru.chaban.lib.MyGson;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MySetTest {
    @Test
    void setList() throws IllegalAccessException {
        MyGson myGson = new MyGson();
        MySet mySet = new MySet();

        Set<String> str = new HashSet<>();
        str.add("Denis");
        str.add("Сергей");
        str.add("Владимир");
        mySet.setSet(str);

        assertEquals("{denis}", myGson.getValueSimple("list", mySet));

    }
}