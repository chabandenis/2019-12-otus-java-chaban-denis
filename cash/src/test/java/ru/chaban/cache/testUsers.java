package ru.chaban.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.chaban.AbstractHibernateTest;
import ru.chaban.core.dao.UserDao;
import ru.chaban.core.model.User;
import ru.chaban.core.service.DBServiceUser;
import ru.chaban.core.service.DbServiceUserImpl;
import ru.chaban.hibernate.dao.UserDaoHibernate;
import ru.chaban.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DisplayName("Демо работы с hibernate (без абстракций) должно ")
public class testUsers extends AbstractHibernateTest {

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
    }

    /*
    Тест без кэширования работает 8 секунд, без кэширования в секунду умещается
    старт без кэш: Thu May 21 11:48:55 NOVT 2020; окончание2: Thu May 21 11:49:03 NOVT 2020
    старт с кэш: Thu May 21 11:49:03 NOVT 2020; окончание2: Thu May 21 11:49:03 NOVT 2020
     */
    @Test
    void shouldCorrectSaveAndLoadUserWithExpectedQueriesCountInTwoDifferentSessions() {

        List<User> users = new ArrayList<>();

        // создадим пользователей
        for (int i = 1; i < 100000; i++) {
            User tmpUser = buildDefaultUser();
            users.add(tmpUser);
        }

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

        // запишем
        users.stream().forEach(x -> dbServiceUser.saveUser(x));

        List<User> loadedUsers = new ArrayList<>();

        Date dateStart = new Date();

        System.out.println("старт: " + dateStart);

        String str = "";


        // прочитаем
        for (User user : users) {
            User tmpUser = dbServiceUser.getUser(user.getId()).get();
            str = tmpUser.getName();
            loadedUsers.add(tmpUser);
        }

        Date dateEnd = new Date();
        System.out.println("старт: " + dateStart + "; окончание: " + dateEnd);


        Date dateStart2 = new Date();
        System.out.println("старт2: " + dateStart);

        str = "";

        // прочитаем c кэш
        for (User user : users) {
            User tmpUser = dbServiceUser.getUserCache(user.getId());
            str = tmpUser.getName();
            loadedUsers.add(tmpUser);
        }
        Date dateEnd2 = new Date();

        System.out.println("старт без кэш: " + dateStart + "; окончание2: " + dateEnd);
        System.out.println("старт с кэш: " + dateStart2 + "; окончание2: " + dateEnd2);
        System.out.println("" + str.length());

    }
}
