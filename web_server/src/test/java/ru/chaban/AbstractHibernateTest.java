package ru.chaban;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.chaban.core.model.Address;
import ru.chaban.core.model.Phone;
import ru.chaban.core.model.User;
import ru.chaban.hibernate.HibernateUtils;

import java.util.Arrays;

public abstract class AbstractHibernateTest {
    protected static final String FIELD_ID = "id";
    protected static final String FIELD_NAME = "name";
    protected static final String TEST_USER_NAME = "Вася";
    protected static final String TEST_USER_NEW_NAME = "НЕ Вася";
    protected static final String TEST_USER_NEW_NAME2 = "Совершенно точно НЕ Вася";
    private static final String HIBERNATE_CFG_XML_FILE_RESOURCE = "hibernate-test.cfg.xml";

    /*
    protected static long user_id = 0;
    protected static long phone_id = 0;
    protected static long adr_id = 0;
     */
    protected SessionFactory sessionFactory;

    @BeforeEach
    public void setUp() {
        Class[] lst = {User.class, Phone.class, Address.class};
        sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_XML_FILE_RESOURCE, lst);
    }

    @AfterEach
    void tearDown() {
        sessionFactory.close();
    }

    protected User buildDefaultUser() {
        User user = new User(TEST_USER_NAME);
        user.setAddresses(new Address("adress 1", user));
        user.setPhones(Arrays.asList(new Phone("phone 1", user)));
        return user;
    }

    protected void saveUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            saveUser(session, user);
        }
    }

    protected void saveUser(Session session, User user) {
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    protected User loadUser(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(User.class, id);
        }
    }

    protected EntityStatistics getUserStatistics() {
        Statistics stats = sessionFactory.getStatistics();
        return stats.getEntityStatistics(User.class.getName());
    }
}
