package ru.chaban.service;

import java.util.Optional;

public interface Crud<T> {
    /*
      захотелось работать с объектами без id в качестве параметра
     */
    void save(T userObject);

    void delete(String id);

    Optional<T> get(String id);
}
