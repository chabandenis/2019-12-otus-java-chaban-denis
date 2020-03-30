package ru.chaban.lib;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;

public class MyGson {

    // метод формирования строки JSON
    public String create(Object myObject) throws ClassNotFoundException, IllegalAccessException {
        if (myObject == null) {
            StringWriter writer = new StringWriter();
            writer.append(null);
            return writer.toString();
        }
        if (isSimple(myObject)) {
            return createSimple(myObject);
            //!!!return createSimpleArray(Json.createArrayBuilder(), myObject).build().toString();
        } else {
            if (isComplexObject(myObject)) {
                return createComplexObject(myObject).build().toString();
            } else {
                return createSimpleObject(Json.createObjectBuilder(), Json.createArrayBuilder(), myObject).build().toString();
            }
        }
    }

    JsonArrayBuilder createComplexObject(Object myObject) throws IllegalAccessException, ClassNotFoundException {
        JsonArrayBuilder aB = Json.createArrayBuilder();
        JsonObjectBuilder jO = Json.createObjectBuilder();

        if (myObject.getClass().toString().contains("class [I")) {
            for (var item : (int[]) myObject) {
                createSimpleArray(aB, item);
            }
        } else if (myObject.getClass().toString().contains("class java.util.Collections")
                || myObject.getClass().toString().contains("interface java.util.Collections")
                || (myObject.getClass().toString().contains("interface java.util.List")
                || myObject.getClass().toString().contains("class java.util.ArrayList"))
        ) {
            for (var item : (List) myObject) {
                if (isSimple(item)) {
                    createSimpleArray(aB, item);
                } else {
                    aB.add(createSimpleObject(Json.createObjectBuilder(), Json.createArrayBuilder(), item));
                }
            }
        } else if (myObject.getClass().toString().contains("class java.util.ImmutableCollections$ListN")) {
            for (var item : (List) myObject) {
                createSimpleArray(aB, item);
            }
        } else if (myObject.getClass().toString().contains("interface java.util.Set")
                || myObject.getClass().toString().contains("class java.util.HashSet")) {

            for (var item : (Set) myObject) {
                if (isSimple(item)) {
                    createSimpleArray(aB, item);
                } else {
                    createSimpleObject(jO, aB, item);
                    aB.add(jO);
                }
            }
        }
        return aB;
    }

    public JsonArrayBuilder createSimpleArray(JsonArrayBuilder aB, Object myObject) throws
            ClassNotFoundException, IllegalAccessException {

        switch (String.valueOf(myObject.getClass())) {

            case ("class java.lang.Byte"):
                aB.add((Byte) myObject);
                break;

            case ("int"):
                aB.add((int) myObject);
                break;

            case ("class java.lang.String"):
                aB.add(String.valueOf(myObject));
                break;

            case ("class java.lang.Integer"):
                aB.add(Integer.parseInt(myObject.toString()));
                break;

            case ("class java.lang.Boolean"):
                aB.add((Boolean) myObject);
                break;

            case ("char"):
                aB.add(String.valueOf(myObject));
                break;

            case ("class java.lang.Double"):
                aB.add((Double) myObject);
                break;

            case ("class java.lang.Float"):
                aB.add(Double.valueOf(myObject.toString()));
                break;

            case ("class java.lang.Short"):
                aB.add((Short) myObject);
                break;

            case ("class java.lang.Long"):
                aB.add((Long) myObject);
                break;

            case ("long"):
                aB.add((Long) myObject);
                break;
        }
        return aB;
    }

    public String createSimple(Object myObject) throws
            ClassNotFoundException, IllegalAccessException {
        String retVal = "";

        switch (String.valueOf(myObject.getClass())) {

            case ("class java.lang.Character"):
                retVal = "\"" + String.valueOf((Character) myObject) + "\"";
                break;

            case ("class java.lang.Byte"):
                retVal = String.valueOf((Byte) myObject);
                break;

            case ("int"):
                retVal = String.valueOf((int) myObject);
                break;

            case ("class java.lang.String"):
                retVal = "\"" + myObject.toString() + "\"";
                break;

            case ("class java.lang.Integer"):
                retVal = String.valueOf(Integer.parseInt(myObject.toString()));
                break;

            case ("class java.lang.Boolean"):
                retVal = String.valueOf((Boolean) myObject);
                break;

            case ("char"):
                retVal = String.valueOf(myObject);
                break;

            case ("class java.lang.Double"):
                retVal = String.valueOf((Double) myObject);
                break;

            case ("class java.lang.Float"):
                retVal = String.valueOf(Double.valueOf(myObject.toString()));
                break;

            case ("class java.lang.Short"):
                retVal = String.valueOf((Short) myObject);
                break;

            case ("class java.lang.Long"):
                retVal = String.valueOf((Long) myObject);
                break;

            case ("long"):
                retVal = String.valueOf((Long) myObject);
                break;
        }
        return retVal;
    }

    public JsonObjectBuilder createSimpleObject(JsonObjectBuilder jO, JsonArrayBuilder aB, Object myObject) throws
            ClassNotFoundException, IllegalAccessException {

        for (var field : myObject.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            if (field.get(myObject) == null) {
                continue;
            }

            switch (String.valueOf(field.getType())) {

                case ("int"):
                    jO.add(field.getName(), field.getInt(myObject));
                    break;

                case ("class java.lang.String"):
                    jO.add(field.getName(), String.valueOf(field.get(myObject)));
                    break;

                case ("class java.lang.Integer"):
                    jO.add(field.getName(), Integer.parseInt(field.get(myObject).toString()));
                    break;

                case ("class java.lang.Boolean"):
                    jO.add(field.getName(), (Boolean) field.get(myObject));
                    break;

                case ("char"):
                    jO.add(field.getName(), String.valueOf(field.get(myObject)));
                    break;

                case ("class java.lang.Double"):
                    jO.add(field.getName(), (Double) field.get(myObject));
                    break;

                case ("class java.lang.Float"):
                    jO.add(field.getName(), Double.valueOf(field.get(myObject).toString()));
                    break;

                case ("class java.lang.Short"):
                    jO.add(field.getName(), (Short) field.get(myObject));
                    break;

                case ("class java.lang.Long"):
                    jO.add(field.getName(), (Long) field.get(myObject));
                    break;

                case ("long"):
                    jO.add(field.getName(), (Long) field.get(myObject));
                    break;

                default:
                    if (isSimple(field.get(myObject))) {
                        jO.add(field.getName(), createSimpleArray(Json.createArrayBuilder(), field.get(myObject)));
                    } else {
                        if (isComplexObject(field.get(myObject))) {
                            jO.add(field.getName(), createComplexObject(field.get(myObject)));
                        } else {
                            jO.add(field.getName(), createSimpleObject(Json.createObjectBuilder(), aB, field.get(myObject)));
                        }
                    }
            }
        }

        return jO;
    }

    boolean isComplexObject(Object myObject) {
        if (myObject.getClass().toString().contains("interface java.util.List")
                || myObject.getClass().toString().contains("class java.util.ArrayList")) {
            return true;
        } else if (myObject.getClass().toString().contains("interface java.util.Set")
                || myObject.getClass().toString().contains("class java.util.HashSet")) {
            return true;
        } else if (myObject.getClass().toString().contains("interface java.util.Map")) {
            return true;
        } else if (myObject.getClass().toString().contains("class [I")) {
            return true;
        } else if (myObject.getClass().toString().contains("class java.util.ImmutableCollections$ListN")) {
            return true;
        } else if (myObject.getClass().toString().contains("class java.util.Collections$SingletonList")) {
            return true;
        }

        return false;
    }

    boolean isSimple(Object myObject) {
        boolean retValue = false;

        switch (String.valueOf(myObject.getClass())) {

            case ("class java.lang.Character"):
                retValue = true;
                break;

            case ("class java.lang.Byte"):
                retValue = true;
                break;

            case ("int"):
                retValue = true;
                break;

            case ("class java.lang.String"):
                retValue = true;
                break;

            case ("class java.lang.Integer"):
                retValue = true;
                break;

            case ("class java.lang.Boolean"):
                retValue = true;
                break;

            case ("char"):
                retValue = true;
                break;

            case ("class java.lang.Double"):
                retValue = true;
                break;

            case ("class java.lang.Float"):
                retValue = true;
                break;

            case ("class java.lang.Short"):
                retValue = true;
                break;

            case ("class java.lang.Long"):
                retValue = true;
                break;

            case ("long"):
                retValue = true;
                break;
        }
        return retValue;
    }
}
