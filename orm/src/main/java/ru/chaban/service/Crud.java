package ru.chaban.service;

import java.sql.SQLException;
import java.util.Optional;

public interface Crud<T> {
    /*
      захотелось работать с объектами без id в качестве параметра
     */
    void save(T userObject) throws SQLException;

    void delete(T userObject);

    Optional<T> get(T userObject);
}
