package ru.chaban.model;

import org.junit.jupiter.api.Test;
import ru.chaban.service.Crud;
import ru.chaban.service.CrudImpl;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {
    @Test
    void myTest() throws SQLException {
        Account account = new Account(Long.valueOf(1), "222", 33.333);
        Crud crud = new CrudImpl();

        // создадим таблицу
        crud.create(account);

        // сохраним
        crud.save(account);

        account.setRest(33.336);

        // сохраним
        crud.save(account);

        // прочитаю из базы с id=1
        Account accountFromDb = new Account(Long.valueOf(1));
        crud.get(accountFromDb);

        assertEquals(account.getNo(), accountFromDb.getNo());
        assertEquals(account.getRest(), accountFromDb.getRest());
        assertEquals(account.getTypeVal(), accountFromDb.getTypeVal());

        // удалим
        crud.delete(account);
    }
}