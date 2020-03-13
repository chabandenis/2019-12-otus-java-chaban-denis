package ru.chaban.exaples;

import org.junit.jupiter.api.Test;
import ru.chaban.lib.MyGson;

class SimpleTest {

    @Test
    void testSimple() throws ClassNotFoundException {

        Simple simple = new Simple();

        MyGson myGson = new MyGson();
        myGson.myStr(simple);



    }
}