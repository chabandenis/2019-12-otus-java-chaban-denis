package ru.chaban.lib;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import ru.chaban.examples.MyList;
import ru.chaban.examples.MySet;
import ru.chaban.examples.Simple;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyGsonTest {

    @Test
    void newTest() throws IllegalAccessException, ClassNotFoundException {
        var gson = new Gson();
        MyGson serializer = new MyGson();
/*
        assertEquals(gson.toJson(null), serializer.create(null));
        assertEquals(gson.toJson((byte) 1), serializer.create((byte) 1));
        assertEquals(gson.toJson((short) 1f), serializer.create((short) 1f));
        assertEquals(gson.toJson(1), serializer.create(1));
        assertEquals(gson.toJson(1L), serializer.create(1L));
        assertEquals(gson.toJson(1f), serializer.create(1f));
        assertEquals(gson.toJson(1d), serializer.create(1d));
        assertEquals(gson.toJson("aaa"), serializer.create("aaa"));
        assertEquals(gson.toJson('a'), serializer.create('a'));
        */
        assertEquals(gson.toJson(new int[]{1, 2, 3}), serializer.create(new int[]{1, 2, 3}));
        assertEquals(gson.toJson(List.of(1, 2, 3)), serializer.create(List.of(1, 2, 3)));
        assertEquals(gson.toJson(Collections.singletonList(1)), serializer.create(Collections.singletonList(1)));

    }

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

        System.out.println("gson: " + new Gson().toJson(tmpList));
        System.out.println("my gson: " + myGson.create(tmpList));

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

    @Test
    void mySimple2() throws ClassNotFoundException, IllegalAccessException {
        C c = new C();
        c.a = new A();
        c.a.x = 1;
        c.b = new B();
        c.b.y = 2;
        c.z = 3;

        MyGson myGson = new MyGson();

        System.out.println("gson: " + new Gson().toJson(c));
        System.out.println("my gson: " + myGson.create(c));
        assertEquals(new Gson().toJson(c), myGson.create(c));
    }

    @Test
    void mySimpleSet() throws ClassNotFoundException, IllegalAccessException {
        Set<A> a = new HashSet<>();
        A myA = new A();
        myA.x = 55;
        a.add(myA);
        A myA2 = new A();
        myA2.x = 66;
        a.add(myA2);

        MyGson myGson = new MyGson();

        System.out.println("gson: " + new Gson().toJson(a));
        System.out.println("my gson: " + myGson.create(a));
        assertEquals(new Gson().toJson(a), myGson.create(a));
    }
}

class A {
    public int x;
}

class B {
    public int y;
}

class C {
    public A a;
    public B b;
    int z;
}