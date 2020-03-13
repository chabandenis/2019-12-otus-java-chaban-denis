package ru.chaban.lib;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import ru.chaban.exaples.MySet;

import javax.json.Json;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals(new Gson().toJson(mySet), myGson.myStr(mySet));
    }

}