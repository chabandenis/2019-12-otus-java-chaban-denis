package ru.chaban.service;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
    Над объектом возвращает список полей с данными для БД
 */
public class FieldsForDbImpl implements FieldsForDb {

    private List<FieldsInfo> fieldsInfo = new ArrayList<>();

    @Override
    public List<FieldsInfo> getFieldsAndValues(Object object) {
        try {
            create(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //fieldsInfo.forEach(x -> System.out.println(x));
        return fieldsInfo;
    }

    // метод формирования строки JSON
    public void create(Object myObject) throws IllegalAccessException {
        createSimpleObject(Json.createObjectBuilder(), Json.createArrayBuilder(), myObject);
    }

    public void createSimpleObject(JsonObjectBuilder jO, JsonArrayBuilder aB, Object myObject) throws IllegalAccessException {

        for (var field : myObject.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            if (field.get(myObject) == null) {
                continue;
            }

            switch (String.valueOf(field.getType())) {

                case ("int"):
                    fieldsInfo.add(new FieldsInfo(
                            field.getName().toUpperCase(),
                            String.valueOf(field.getType()),
                            String.valueOf(field.getInt(myObject)),
                            field.isAnnotationPresent(Id.class)));
                    break;

                case ("class java.lang.String"):
                case ("char"):
                    fieldsInfo.add(new FieldsInfo(
                            field.getName().toUpperCase(),
                            String.valueOf(field.getType()),
                            String.valueOf(String.valueOf(field.get(myObject))),
                            field.isAnnotationPresent(Id.class)
                    ));
                    break;

                case ("class java.lang.Integer"):
                    fieldsInfo.add(new FieldsInfo(
                            field.getName().toUpperCase(),
                            String.valueOf(field.getType()),
                            String.valueOf(Integer.parseInt(field.get(myObject).toString())),
                            field.isAnnotationPresent(Id.class)));
                    break;

                case ("class java.lang.Boolean"):
                    fieldsInfo.add(new FieldsInfo(
                            field.getName().toUpperCase(),
                            String.valueOf(field.getType()),
                            String.valueOf((Boolean) field.get(myObject)),
                            field.isAnnotationPresent(Id.class)));
                    break;

                case ("class java.lang.Double"):
                    fieldsInfo.add(new FieldsInfo(
                            field.getName().toUpperCase(),
                            String.valueOf(field.getType()),
                            String.valueOf((Double) field.get(myObject)),
                            field.isAnnotationPresent(Id.class)));
                    break;

                case ("class java.lang.Float"):
                    fieldsInfo.add(new FieldsInfo(
                            field.getName().toUpperCase(),
                            String.valueOf(field.getType()),
                            String.valueOf(Double.valueOf(field.get(myObject).toString())),
                            field.isAnnotationPresent(Id.class)));
                    break;

                case ("class java.lang.Short"):
                    fieldsInfo.add(new FieldsInfo(
                            field.getName().toUpperCase(),
                            String.valueOf(field.getType()),
                            String.valueOf((Short) field.get(myObject)),
                            field.isAnnotationPresent(Id.class)));
                    break;

                case ("class java.lang.Long"):
                case ("long"):
                    fieldsInfo.add(new FieldsInfo(
                            field.getName().toUpperCase(),
                            String.valueOf(field.getType()),
                            String.valueOf((Long) field.get(myObject)),
                            field.isAnnotationPresent(Id.class)));
                    break;
            }
        }
    }
}