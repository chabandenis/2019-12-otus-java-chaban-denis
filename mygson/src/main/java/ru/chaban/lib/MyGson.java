package ru.chaban.lib;

import java.lang.reflect.Field;
import java.util.Arrays;

public class MyGson {

    public String myStr(Object myObject) throws ClassNotFoundException {

        Field[] fields = myObject.getClass().getFields();

        System.out.println("Поля в классе");
        Arrays.asList(fields).stream().forEach(field -> {
            System.out.println(field.getName() + "; " + field.getType() + "; " + getValueSimple(field, myObject));
        });

        return null;
    }

    private String getValueSimple(Field field, Object myObject) {
        String retValue = "";

        try {
            switch (String.valueOf(field.getType())) {
                case ("int"):

                    retValue = String.valueOf(field.getInt(myObject));

                    break;
                case ("class java.lang.String"):
                    retValue = "2";
                    break;
                case ("class java.lang.Integer"):
                    retValue = "3";
                    break;
                default:
                    retValue = "error";
                    break;
            }
        } catch (IllegalAccessException e) {
            retValue = "error";
            e.printStackTrace();
        }
        return retValue;
    }
}
