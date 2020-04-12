package ru.chaban.service;

import java.util.List;
import java.util.stream.Collectors;

public class CreateSQLImpl implements CreateSQL {
    private FieldsForDb fieldsForDb = new FieldsForDbImpl();
    private Conformity conformity = new Conformity();

    @Override
    public String createTableSQL(Object object) {
        List<FieldsInfo> fieldsInfoList = fieldsForDb.getFieldsAndValues(object);

        if (fieldsInfoList.size() == 0) {
            return null;
        }

        StringBuilder retValue = new StringBuilder();
        retValue.append("CREATE TABLE ");
        String[] spl = object.getClass().getName().split(".");

        retValue.append(spl[spl.length] + " ");
        retValue.append("(");

        retValue.append(fieldsInfoList
                .stream().map(x -> x.getName() + " " + conformity.get(x.getType()))
                .collect(Collectors.joining(", ")));

        retValue.append(");");

        System.out.println(retValue);

        return retValue.toString();

    }

    @Override
    public String insertTableSQL(Object object) {
        fieldsForDb.getFieldsAndValues(object);

        return null;
    }

    @Override
    public String updateTableSQL(Object object) {
        fieldsForDb.getFieldsAndValues(object);
        return null;
    }

    @Override
    public String deleteTableSQL(Object object) {
        fieldsForDb.getFieldsAndValues(object);

        return null;
    }
}
