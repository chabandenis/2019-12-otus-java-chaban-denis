package ru.chaban.service;

import java.util.Optional;

public interface Crud<T> {
    /*
      захотелось работать с объектами без id в качестве параметра
     */
    long save(T userObject);

    void delete(T userObject);

    Optional<T> get(T userObject);
}
