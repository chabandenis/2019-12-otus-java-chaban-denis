package ru.chaban.exaples;

import org.junit.jupiter.api.Test;
import ru.chaban.lib.MyGson;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleTest {

    @Test
    void testSimple() throws ClassNotFoundException, IllegalAccessException {

        Simple simple = new Simple();

        MyGson myGson = new MyGson();

        assertEquals("0", myGson.getValueSimple("i", simple));
        assertEquals("null", myGson.getValueSimple("str", simple));
        assertEquals("null", myGson.getValueSimple("iH", simple));
        assertEquals("null", myGson.getValueSimple("iH", simple));
        assertEquals("null", myGson.getValueSimple("aBoolean", simple));
        assertEquals(String.valueOf('\u0000'), myGson.getValueSimple("aChar", simple));
        assertEquals("null", myGson.getValueSimple("aDouble", simple));
        assertEquals("null", myGson.getValueSimple("aFloat", simple));
        assertEquals("null", myGson.getValueSimple("aShort", simple));
        assertEquals("null", myGson.getValueSimple("aLong", simple));

        simple.setI(1);
        simple.setiH(8);
        simple.setStr("123");
        simple.setaBoolean(true);
        simple.setaChar('a');
        simple.setaDouble(123.456);
        simple.setaFloat(123.6f);
        simple.setaShort((short) 12345);
        simple.setaLong(1234567890L);

        assertEquals("1", myGson.getValueSimple("i", simple));
        assertEquals("123", myGson.getValueSimple("str", simple));
        assertEquals("8", myGson.getValueSimple("iH", simple));
        assertEquals(String.valueOf(true), myGson.getValueSimple("aBoolean", simple));
        assertEquals(String.valueOf('a'), myGson.getValueSimple("aChar", simple));
        assertEquals(String.valueOf(123.456), myGson.getValueSimple("aDouble", simple));
        assertEquals(String.valueOf(123.6f), myGson.getValueSimple("aFloat", simple));
        assertEquals(String.valueOf((short) 12345), myGson.getValueSimple("aShort", simple));
        assertEquals(String.valueOf(1234567890L), myGson.getValueSimple("aLong", simple));
    }
}