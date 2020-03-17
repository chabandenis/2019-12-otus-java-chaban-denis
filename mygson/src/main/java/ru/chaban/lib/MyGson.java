package ru.chaban.lib;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyGson {

    // метод формирования строки JSON
    public String create(Object myObject) throws ClassNotFoundException, IllegalAccessException {
        var jO = Json.createObjectBuilder();

        // поля объекта
        for (var field : myObject.getClass().getFields()) {

            if (field.get(myObject) == null) {
                continue;
            }

            switch (String.valueOf(field.getType())) {
                case ("interface java.util.Set"): {
                    var jsonArrayBuilder = Json.createArrayBuilder();

                    for (var item : (Set) field.get(myObject)) {
                        jsonArrayBuilder.add(item.toString());
                    }

                    jO.add(field.getName(), jsonArrayBuilder);
                    break;
                }

                case ("interface java.util.List"): {
                    var jsonArrayBuilder = Json.createArrayBuilder();

                    for (var item : (List) field.get(myObject)) {
                        jsonArrayBuilder.add(item.toString());
                    }

                    jO.add(field.getName(), jsonArrayBuilder);

                    break;
                }

                case ("interface java.util.Map"): {
                    var jsonArrayBuilder = Json.createArrayBuilder();
                    var jsonObjectBuilder = Json.createObjectBuilder();

                    Map tmpMap = (Map) field.get(myObject);

                    for (var item : tmpMap.keySet()) {
                        jsonObjectBuilder.add(item.toString(), tmpMap.get(item).toString());
                    }
                    //jsonArrayBuilder.add(jsonObjectBuilder);
                    jO.add(field.getName(), jsonObjectBuilder);

                    break;
                }

                case ("interfsace java.util.Map"): {
                    var jsonArrayBuilder = Json.createArrayBuilder();
                    var jsonObjectBuilder = Json.createObjectBuilder();

                    Map tmpMap = (Map) field.get(myObject);

                    for (var item : tmpMap.keySet()) {
                        jsonObjectBuilder.add(item.toString(), tmpMap.get(item).toString());
                    }
                    jsonArrayBuilder.add(jsonObjectBuilder);
                    jO.add(field.getName(), jsonArrayBuilder);

                    break;
                }
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
            }
        }
        return jO.build().toString();
    }

    public JsonArrayBuilder getValueSimple(String field, Object myObject) throws IllegalAccessException {

        /*
        System.out.println("Поля в классе");
        Arrays.asList(myObject.getClass().getFields()).stream().forEach(fl -> {
            try {
                System.out.println(fl.getName() + "; " + fl.getType() + "; " + getValueSimple(fl, myObject));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
*/

 /*       for (var fl : myObject.getClass().getFields()) {
            if (fl.getName().equals(field)) {
                return getValueSimple(fl, myObject);
            }
        }*/
        return null;
    }

    public String getValueSimpleNum(Field field, Object myObject) throws IllegalAccessException {
        String retValue = "";
        var columnValue = field.get(myObject);

        if (columnValue == null) {
            return null;
        }

        switch (String.valueOf(field.getType())) {
            case ("int"):
                retValue = String.valueOf(field.getInt(myObject));
                break;

            case ("class java.lang.String"):
                retValue = String.valueOf(columnValue);
                break;

            case ("class java.lang.Integer"):
                retValue = String.valueOf(columnValue);
                break;

            case ("class java.lang.Boolean"):
                //return String.valueOf("null");
                retValue = String.valueOf(columnValue);
                break;

            case ("char"):
                retValue = String.valueOf(columnValue);
                break;

            case ("class java.lang.Double"):
                retValue = String.valueOf(columnValue);
                break;

            case ("class java.lang.Float"):
                retValue = String.valueOf(columnValue);
                break;

            case ("class java.lang.Short"):
                retValue = String.valueOf(columnValue);
                break;

            case ("class java.lang.Long"):
                retValue = String.valueOf(columnValue);
                break;

            default:
                retValue = "error";
                break;
        }

        return retValue;
    }

    public String getValueSimple(Field field, Object myObject) throws IllegalAccessException {
        String retValue = "";
        var columnValue = field.get(myObject);

        if (columnValue == null) {
            return null;
        }

        switch (String.valueOf(field.getType())) {
            case ("int"):
                retValue = String.valueOf(field.getInt(myObject));
                break;

            case ("class java.lang.String"):
                retValue = String.valueOf(columnValue);
                break;

            case ("class java.lang.Integer"):
                retValue = String.valueOf(columnValue);
                break;

            case ("class java.lang.Boolean"):
                //return String.valueOf("null");
                retValue = String.valueOf(columnValue);
                break;

            case ("char"):
                retValue = String.valueOf(columnValue);
                break;

            case ("class java.lang.Double"):
                retValue = String.valueOf(columnValue);
                break;

            case ("class java.lang.Float"):
                retValue = String.valueOf(columnValue);
                break;

            case ("class java.lang.Short"):
                retValue = String.valueOf(columnValue);
                break;

            case ("class java.lang.Long"):
                retValue = String.valueOf(columnValue);
                break;

            default:
                retValue = "error";
                break;
        }

        return retValue;
    }

    public JsonArrayBuilder getSetValue(Field field, Object myObject) throws IllegalAccessException {
        switch (String.valueOf(field.getType())) {
            case ("interface java.util.Set"):
                var jsonArrayBuilder = Json.createArrayBuilder();

                for (var item : (Set) field.get(myObject)) {
                    jsonArrayBuilder.add(item.toString());
                }

                return jsonArrayBuilder;
        }
        return null;
    }


    public JsonArrayBuilder getValueList(Field field, Object myObject) throws IllegalAccessException {
        // JsonArrayBuilder retValue = "";

        try {

            switch (String.valueOf(field.getType())) {
            /*    case ("int"):
                    retValue = String.valueOf(field.getInt(myObject));
                    break;

                case ("class java.lang.String"):
                    retValue = String.valueOf(columnValue);
                    break;

                case ("class java.lang.Integer"):
                    retValue = String.valueOf(columnValue);
                    break;

                case ("class java.lang.Boolean"):
                    if (columnValue == null) {
                        //return String.valueOf("null");
                    }
                    retValue = String.valueOf(columnValue);
                    break;

                case ("char"):
                    retValue = String.valueOf(columnValue);
                    break;

                case ("class java.lang.Double"):
                    retValue = String.valueOf(columnValue);
                    break;

                case ("class java.lang.Float"):
                    retValue = String.valueOf(columnValue);
                    break;

                case ("class java.lang.Short"):
                    retValue = String.valueOf(columnValue);
                    break;

                case ("class java.lang.Long"):
                    retValue = String.valueOf(columnValue);
                    break;

                case ("interface java.util.List"):
                    for (var tmp : (List) columnValue) {
                        retValue += tmp + "; ";
                    }
                    break;
*/
                case ("interface java.util.Set"):
                    var jsonArrayBuilder = Json.createArrayBuilder();
                    var jsonObjectBuilder = Json.createObjectBuilder();

                    for (var item : (Set) field.get(myObject)) {
                        jsonArrayBuilder.add(item.toString());
                    }
                    ;
                    //jsonArrayBuilder.add(jsonObjectBuilder);

                    return jsonArrayBuilder;

                default:
                    //retValue = "error";
                    break;
            }
        } catch (IllegalAccessException e) {
            // retValue = "error";
            e.printStackTrace();
        }
        return null;
    }
}
