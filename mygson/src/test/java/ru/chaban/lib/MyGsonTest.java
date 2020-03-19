package ru.chaban.lib;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import ru.chaban.examples.MyList;
import ru.chaban.examples.MyMap;
import ru.chaban.examples.MySet;
import ru.chaban.examples.Simple;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyGsonTest {

    @Test
    void mySet() throws ClassNotFoundException, IllegalAccessException {
        MyGson myGson = new MyGson();
        MySet mySet = new MySet();

        Set<String> str = new HashSet<>();
        str.add("Denis");
        str.add("Сергей");
        str.add("Владимир");
        mySet.setSet(str);

        System.out.println("gson: " + new Gson().toJson(mySet));
        System.out.println("my gson: " + myGson.create(mySet));
        assertEquals(new Gson().toJson(mySet), myGson.create(mySet));
    }

    @Test
    void myList() throws ClassNotFoundException, IllegalAccessException {
        MyGson myGson = new MyGson();
        MyList myList = new MyList();

        List tmpList = new ArrayList();
        tmpList.add("Denis");
        tmpList.add("Сергей");
        tmpList.add("Владимир");

        myList.setList(tmpList);

        System.out.println("gson: " + new Gson().toJson(myList));
        System.out.println("my gson: " + myGson.create(myList));
        assertEquals(new Gson().toJson(myList), myGson.create(myList));
    }

    @Test
    void myMap() throws ClassNotFoundException, IllegalAccessException {
        MyGson myGson = new MyGson();
        MyMap myList = new MyMap();

        Map<String, String> myMap = new HashMap<>();
        myMap.put("name", "Denis");
        myMap.put("age", "19");
        myMap.put("sex", "male");

        myList.setMap(myMap);

        System.out.println("gson: " + new Gson().toJson(myList));
        System.out.println("my gson: " + myGson.create(myList));
        assertEquals(new Gson().toJson(myList), myGson.create(myList));
    }

    @Test
    void mySimple() throws ClassNotFoundException, IllegalAccessException {
        Simple simple = new Simple();

        MyGson myGson = new MyGson();

        System.out.println("gson: " + new Gson().toJson(simple));
        System.out.println("my gson: " + myGson.create(simple));
        assertEquals(new Gson().toJson(simple), myGson.create(simple));

        simple.setI(1);
        simple.setiH(8);
        simple.setStr("a123");
        simple.setaBoolean(true);
        simple.setaChar('a');
        simple.setaDouble(123.456);
        simple.setaFloat(123.6f);
        simple.setaShort((short) 12345);
        simple.setaLong(1234567890L);

        simple.setI(1);
        simple.setiH(8);
        simple.setStr("a123");
        simple.setaBoolean(true);
        simple.setaChar('a');
        simple.setaDouble(123.456);
        simple.setaFloat(123.6f);
        simple.setaShort((short) 12345);
        simple.setaLong(1234567890L);

        simple.setiPrivate(1);
        simple.setiHPrivate(8);
        simple.setStrPrivate("a123");
        simple.setaBooleanPrivate(true);
        simple.setaCharPrivate('a');
        simple.setaDoublePrivate(123.456);
        simple.setaFloatPrivate(123.6f);
        simple.setaShortPrivate((short) 12345);
        simple.setaLongPrivate(1234567890L);

        MySet mySet1 = new MySet();
        mySet1.set.add("01-01");
        mySet1.set.add("01-02");
        simple.mySets.add(mySet1);

        MySet mySet2 = new MySet();
        mySet2.set.add("02-01");
        mySet2.set.add("02-02");
        simple.mySets.add(mySet2);

        MySet mySet3 = new MySet();
        mySet3.set.add("03-01");
        mySet3.set.add("03-02");
        simple.mySets.add(mySet3);

        System.out.println("gson: " + new Gson().toJson(simple));
        System.out.println("my gson: " + myGson.create(simple));
        assertEquals(new Gson().toJson(simple), myGson.create(simple));
    }
}