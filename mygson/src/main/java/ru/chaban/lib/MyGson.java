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

    public String getValueSimple(String field, Object myObject) {
/*
        System.out.println("Поля в классе");
        Arrays.asList(myObject.getClass().getFields()).stream().forEach(fl -> {
            System.out.println(fl.getName() + "; " + fl.getType() + "; " + getValueSimple(fl, myObject));
        });


 */
        for (var fl : myObject.getClass().getFields()) {
            if (fl.getName().equals(field)) {
                return (getValueSimple(fl, myObject));
            }
        }
        return null;
    }

    public String getValueSimple(Field field, Object myObject) {
        String retValue = "";

        try {
            switch (String.valueOf(field.getType())) {
                case ("int"):
                    retValue = String.valueOf(field.getInt(myObject));
                    break;

                case ("class java.lang.String"):
                    retValue = String.valueOf(field.get(myObject));
                    break;

                case ("class java.lang.Integer"):
                    retValue = String.valueOf(field.get(myObject));
                    break;

                case ("class java.lang.Boolean"):
                    if (field.get(myObject) == null) {
                        return String.valueOf("null");
                    }
                    retValue = String.valueOf(field.get(myObject));
                    break;

                case ("char"):
                    retValue = String.valueOf(field.get(myObject));
                    break;

                case ("class java.lang.Double"):
                    retValue = String.valueOf(field.get(myObject));
                    break;

                case ("class java.lang.Float"):
                    retValue = String.valueOf(field.get(myObject));
                    break;

                case ("class java.lang.Short"):
                    retValue = String.valueOf(field.get(myObject));
                    break;

                case ("class java.lang.Long"):
                    retValue = String.valueOf(field.get(myObject));
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
