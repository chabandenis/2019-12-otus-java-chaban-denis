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

        Account accountFromDb = new Account(Long.valueOf(1));
        crud.get(accountFromDb.getNo());

        assertEquals(account.getNo(), accountFromDb.getNo());
        assertEquals(account.getRest(), accountFromDb.getRest());
        assertEquals(account.getTypeVal(), accountFromDb.getTypeVal());

        account.setRest(33.334);

        // найдем существующую запись и изменим значение
        crud.save(account);

        crud.get(accountFromDb);

        assertEquals(account.getNo(), accountFromDb.getNo());
        assertEquals(account.getRest(), accountFromDb.getRest());
        assertEquals(account.getTypeVal(), accountFromDb.getTypeVal());

        // удалим
        crud.delete(account);
        assertEquals(Long.valueOf(-1), account.getNo());
        assertEquals(null, account.getRest());
        assertEquals(null, account.getTypeVal());
    }
}