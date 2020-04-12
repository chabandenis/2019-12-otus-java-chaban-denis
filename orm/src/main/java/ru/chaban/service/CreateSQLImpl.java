package ru.chaban.service;

import java.util.List;
import java.util.stream.Collectors;

public class CreateSQLImpl implements CreateSQL {
    private Conformity conformity = new Conformity();

    @Override
    public String createTableSQL(Object object) {
        FieldsForDb fieldsForDb = new FieldsForDbImpl();
        List<FieldsInfo> fieldsInfoList = fieldsForDb.getFieldsAndValues(object);

        if (fieldsInfoList.size() == 0) {
            return null;
        }

        StringBuilder retValue = new StringBuilder();
        retValue.append("CREATE TABLE ");

        String[] spl = object.getClass().getName().split("\\.");

        retValue.append("T_" + (spl[spl.length - 1] + " ").toUpperCase());
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
        FieldsForDb fieldsForDb = new FieldsForDbImpl();
        List<FieldsInfo> fieldsInfoList = fieldsForDb.getFieldsAndValues(object);

        if (fieldsInfoList.size() == 0) {
            return null;
        }

        StringBuilder retValue = new StringBuilder();
        retValue.append("INSERT INTO ");

        String[] spl = object.getClass().getName().split("\\.");

        retValue.append("T_" + (spl[spl.length - 1] + " ").toUpperCase());
        retValue.append("(");

        retValue.append(fieldsInfoList
                .stream().map(x -> x.getName())
                .collect(Collectors.joining(", ")));

        retValue.append(") VALUES (");


        retValue.append(fieldsInfoList
                .stream().map(
                        x -> (conformity.get(x.getType()).contains("CHAR")) ? "'" + x.getValue() + "'" : x.getValue())
                .collect(Collectors.joining(", ")));

        retValue.append(");");

        System.out.println(retValue);

        return retValue.toString();
    }

    @Override
    public String updateTableSQL(Object object) {
        FieldsForDb fieldsForDb = new FieldsForDbImpl();
        List<FieldsInfo> fieldsInfoList = fieldsForDb.getFieldsAndValues(object);

        if (fieldsInfoList.size() == 0) {
            return null;
        }

        StringBuilder retValue = new StringBuilder();
        retValue.append("UPDATE ");

        String[] spl = object.getClass().getName().split("\\.");

        retValue.append("T_" + (spl[spl.length - 1] + " ").toUpperCase());
        retValue.append("(");

        retValue.append(fieldsInfoList
                .stream()
                .filter(x->x.getKey()==false)
                .map(x -> x.getName())
                .collect(Collectors.joining(", ")));

        retValue.append(") VALUES (");


        retValue.append(fieldsInfoList
                .stream()
                .filter(x->x.getKey()==false)
                .map(
                        x -> x.getName() + "=" + ((conformity.get(x.getType()).contains("CHAR")) ? "'" + x.getValue() + "'" : x.getValue()))
                .collect(Collectors.joining(", ")));

        retValue.append(")");

        retValue.append(" WHERE ");

        retValue.append(fieldsInfoList.stream().filter(x -> x.getKey() == true).map(x -> x.getName() + "=" + x.getValue()).collect(Collectors.joining()));

        retValue.append(";");

        System.out.println(retValue);

        return retValue.toString();
    }

    @Override
    public String deleteTableSQL(Object object) {
        FieldsForDb fieldsForDb = new FieldsForDbImpl();
        List<FieldsInfo> fieldsInfoList = fieldsForDb.getFieldsAndValues(object);

        if (fieldsInfoList.size() == 0) {
            return null;
        }

        StringBuilder retValue = new StringBuilder();
        retValue.append("DELETE FROM TABLE ");

        String[] spl = object.getClass().getName().split("\\.");

        retValue.append("T_" + (spl[spl.length - 1] + " ").toUpperCase());
        retValue.append(" WHERE ");

        retValue.append(fieldsInfoList.stream().filter(x -> x.getKey() == true).map(x -> x.getName() + "=" + x.getValue()).collect(Collectors.joining()));

        retValue.append(";");

        System.out.println(retValue);

        return retValue.toString();
    }
}
