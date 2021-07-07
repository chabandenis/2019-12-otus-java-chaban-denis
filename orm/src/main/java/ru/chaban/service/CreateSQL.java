package ru.chaban.service;

public interface CreateSQL {
    String createTableSQL(Object object);

    String insertTableSQL(Object object);

    String updateTableSQL(Object object);

    String deleteTableSQL(Object object);

    String selectTableSQL(Object object);
}
