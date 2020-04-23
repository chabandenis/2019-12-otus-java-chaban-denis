package ru.chaban.model;

import org.junit.jupiter.api.Test;
import ru.chaban.service.Crud;
import ru.chaban.service.CrudImpl;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void myTest() throws SQLException {
        User user = new User(Long.valueOf(1), "222", 4);
        Crud crud = new CrudImpl();

        // создадим таблицу
        crud.create(user);

        // сохраним
        crud.save(user);

//!!        user.setAge(6);

        // сохраним
//!!        crud.save(user);

        // прочитаю из базы с id=1
        User userFromDb = new User(Long.valueOf(1));
        userFromDb = (User) crud.get(userFromDb).get();

        assertEquals(user.getId(), userFromDb.getId());
        assertEquals(user.getName(), userFromDb.getName());
        assertEquals(user.getAge(), userFromDb.getAge());

        user.setAge(5);

        // найдем существующую запись и изменим значение
        crud.save(user);

        crud.get(userFromDb);

        assertEquals(user.getId(), userFromDb.getId());
        assertEquals(user.getName(), userFromDb.getName());
        assertEquals(user.getAge(), userFromDb.getAge());

        // удалим
        crud.delete(user);
        assertEquals(-1, user.getId());
        assertEquals(null, user.getName());
        assertEquals(null, user.getAge());
    }
}