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
import java.util.List;

public abstract class AbstractHibernateTest {
    private static final String HIBERNATE_CFG_XML_FILE_RESOURCE = "hibernate-test.cfg.xml";

    protected static final String FIELD_ID = "id";
    protected static final String FIELD_NAME = "name";
    protected static final String TEST_USER_NAME = "Вася";
    protected static final String TEST_USER_NEW_NAME = "НЕ Вася";
    protected static final String TEST_USER_NEW_NAME2 = "Совершенно точно НЕ Вася";
//    protected static final Address TEST_USER_ADDR = new Address(1, "Адрес", new User());
    protected static final List<Phone> TEST_USER_PHONES = Arrays.asList(new Phone(1, "тел 1"),
            new Phone(2, "тел 2"));


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
        return new User(0, TEST_USER_NAME);
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
