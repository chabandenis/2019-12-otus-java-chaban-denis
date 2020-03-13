package ru.chaban.lib;

import javax.json.Json;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class MyGson {

    public String myStr(Object myObject) throws ClassNotFoundException, IllegalAccessException {

        var jO = Json.createObjectBuilder();

        System.out.println("Поля в классе");
        for (var field : myObject.getClass().getFields()) {
            System.out.println("field " + field.getType());
            if(field.getType().toString().equals("interface java.util.Set")) {
                jO.add(field.getName(), Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add(getValueSimple(field, myObject)))
                        )

            }
            else
            {
                jO.add(field.getName(), getValueSimple(field, myObject));
            }
        }

        return jO.build().toString();


    }

    public String getValueSimple(String field, Object myObject) throws IllegalAccessException {

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

        for (var fl : myObject.getClass().getFields()) {
            if (fl.getName().equals(field)) {
                return (getValueSimple(fl, myObject));
            }
        }
        return null;
    }

    public String getValueSimple(Field field, Object myObject) throws IllegalAccessException {
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

                case ("interface java.util.List"):
                    for (var tmp : (List) field.get(myObject)) {
                        retValue += tmp + "; ";
                    }
                    break;

                case ("interface java.util.Set"):
                    var js = Json.createArrayBuilder()
                            .add(Json.createObjectBuilder();
                                    .add(getValueSimple(field, myObject)))
                        )

                    Arrays.asList((Set) field.get(myObject)).stream().forEach((x)->{js.add(x);});

                    for (var fl : ) {
                        js.add(fl)
                    }

                    for (int i=0; i< lst.size(); i++){
                        if (i==lst.size()-1){
                            retValue+=lst.get(i);
                        } else {
                            retValue+="\"" + lst.get(i) + ", ";
                        }
                    }
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
