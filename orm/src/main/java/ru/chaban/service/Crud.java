package ru.chaban.service;

import java.sql.SQLException;
import java.util.Optional;

public interface Crud<T> {

    void create(T userObject) throws SQLException;

    void save(T userObject) throws SQLException;

    void delete(T userObject) throws SQLException;

    Optional<T> get(T userObject) throws SQLException;
}
