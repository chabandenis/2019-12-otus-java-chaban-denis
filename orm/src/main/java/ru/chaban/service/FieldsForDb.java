package ru.chaban.service;

import java.util.List;

/*
    Соответствие
 */
public interface FieldsForDb {
    List<FieldsInfo> getFieldsAndValues(Object object);
    List<FieldsInfo> getFieldsWithoutValues(Object object);
}
