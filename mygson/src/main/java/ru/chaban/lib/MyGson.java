package ru.chaban.lib;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.util.List;
import java.util.Set;

public class MyGson {

    //private JsonArrayBuilder jsonArray;

    // метод формирования строки JSON
    public String create(Object myObject) throws ClassNotFoundException, IllegalAccessException {
        //jsonArray = Json.createArrayBuilder();
        return create2(myObject);
    }

    public String create2(Object myObject) throws ClassNotFoundException, IllegalAccessException {

        if (myObject.getClass().toString().contains("interface java.util.Set")
                || myObject.getClass().toString().contains("class java.util.HashSet")) {
            var aB = Json.createArrayBuilder();
            for (var item : (Set) myObject) {
                aB.add(createSimple(item));
            }
            return aB.build().toString();

        } else if (myObject.getClass().toString().contains("interface java.util.List")
                || myObject.getClass().toString().contains("class java.util.ArrayList")
        ) {
            var aB = Json.createArrayBuilder();

            for (var item : (List) myObject) {
                aB.add(createSimple(item));
            }

            return aB.build().toString();
        } else {
            return createSimple(myObject).build().toString();
        }
        //return "";
    }

    public JsonArrayBuilder createSimple(Object myObject) throws ClassNotFoundException, IllegalAccessException {

        var aB = Json.createArrayBuilder();

        switch (String.valueOf(myObject.getClass())) {
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

            default: {
                var jO = Json.createObjectBuilder();

                // поля объекта
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
                            jO.add(field.getName(), createSimple(field.get(myObject)));
                    }
                }
                return jO;
            }
        }
        return aB;
    }

    public JsonObjectBuilder createSimple(Object myObject) throws ClassNotFoundException, IllegalAccessException {

        var aB = Json.createArrayBuilder();

        switch (String.valueOf(myObject.getClass())) {
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

            default: {
                var jO = Json.createObjectBuilder();

                // поля объекта
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
                            jO.add(field.getName(), createSimple(field.get(myObject)));
                    }
                }
                return jO;
            }
        }
        return aB;
    }
}

       /*
        case (): {
            var jsonArrayBuilder = Json.createArrayBuilder();

            for (var item : (Set) field.get(myObject)) {
                //((Set) field.get(myObject)).size()
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

         */