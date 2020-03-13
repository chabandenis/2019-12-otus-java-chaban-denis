package ru.chaban.exaples;

import org.junit.jupiter.api.Test;
import ru.chaban.lib.MyGson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {

    @Test
    void getList() throws IllegalAccessException {

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