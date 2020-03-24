package ru.chaban.lib;

import javax.json.Json;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyGson {

    // метод формирования строки JSON
    public String create(Object myObject) throws ClassNotFoundException, IllegalAccessException {
        var jO = Json.createObjectBuilder();

        // поля объекта
        for (var field : myObject.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            if (field.get(myObject) == null) {
                continue;
            }

            switch (String.valueOf(field.getType())) {
                case ("interface java.util.Set"): {
                    var jsonArrayBuilder = Json.createArrayBuilder();

                    for (var item : (Set) field.get(myObject)) {
                        //((Set) field.get(myObject)).size()
                        jsonArrayBuilder.add(   create(item).toString());
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

                default:
                    jO.add(field.getName(), create(field.get(myObject)));
            }
        }
        return jO.build().toString();
    }
}
