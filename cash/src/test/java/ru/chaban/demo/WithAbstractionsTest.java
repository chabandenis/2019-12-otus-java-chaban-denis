package ru.chaban.demo;

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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Демо работы с hibernate (с абстракциями) должно ")
public class WithAbstractionsTest extends AbstractHibernateTest {

    private DBServiceUser dbServiceUser;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        dbServiceUser = new DbServiceUserImpl(userDao);
    }

    //f111
    @Test
    @DisplayName(" корректно сохранять пользователя")
    void shouldCorrectSaveUser() {
        User savedUser = buildDefaultUser();
        System.out.println("~~~" + savedUser.toString());
        long id = dbServiceUser.saveUser(savedUser);
        System.out.println("+++" + savedUser.toString());
        User loadedUser = loadUser(id);

        //assertThat(loadedUser).isNotNull().isEqualToComparingFieldByField(savedUser);
        assertEquals(true, loadedUser.equals(savedUser));

        System.out.println(savedUser);
        System.out.println(loadedUser);
    }

    @Test
    @DisplayName(" корректно загружать пользователя")
    void shouldLoadCorrectUser() {
        User savedUser = buildDefaultUser();
        System.out.println("~~~" + savedUser.toString());
        saveUser(savedUser);
        System.out.println("+++" + savedUser.toString());

        Optional<User> mayBeUser = dbServiceUser.getUser(savedUser.getId());

        assertEquals(true, mayBeUser.get().equals(savedUser));

        System.out.println(savedUser);
        mayBeUser.ifPresent(System.out::println);
    }

    //222
    @Test
    @DisplayName(" корректно изменять ранее сохраненного пользователя")
    void shouldCorrectUpdateSavedUser() {
        User savedUser = buildDefaultUser();
        System.out.println("~~~" + savedUser.toString());
        saveUser(savedUser);
        System.out.println("+++" + savedUser.toString());

        User savedUser2 = new User(savedUser.getId(), TEST_USER_NEW_NAME);
        System.out.println("qqq" + savedUser2.toString());
        long id = dbServiceUser.saveUser(savedUser2);
        System.out.println("qqq" + savedUser2.toString());
        User loadedUser = loadUser(id);
        System.out.println("---" + loadedUser.toString());

        //assertThat(loadedUser).isNotNull().isEqualToComparingFieldByField(savedUser2);
        assertEquals(true, loadedUser.equals(savedUser2));

        System.out.println(savedUser);
        System.out.println(savedUser2);
        System.out.println(loadedUser);
    }

}
