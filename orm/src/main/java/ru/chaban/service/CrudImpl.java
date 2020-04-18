package ru.chaban.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chaban.h2.DataSourceH2;
import ru.chaban.jdbc.DbExecutor;
import ru.chaban.jdbc.ExecutorDemo;
import ru.chaban.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrudImpl<T> implements Crud<T> {
    private static final String URL = "jdbc:h2:mem:";
    private static Logger logger = LoggerFactory.getLogger(ExecutorDemo.class);
    private static CreateSQL createSQL = new CreateSQLImpl();

    private static DataSource dataSource = new DataSourceH2();
    private static SessionManagerJdbc sessionManagerJdbc = new SessionManagerJdbc(dataSource);

    @Override
    public void create(T userObject) throws SQLException {
        logger.info("Создаю таблицу для: {}", userObject);
        sessionManagerJdbc.beginSession();
        executeSQL(sessionManagerJdbc.getCurrentSession().getConnection(), createSQL.createTableSQL(userObject));
        sessionManagerJdbc.commitSession();
        logger.info("Таблица создана успешно");
    }

    @Override
    public void save(T userObject) throws SQLException {
        logger.info("Сохраняю значения для: {}", userObject);
        sessionManagerJdbc.beginSession();
        executeSQL(sessionManagerJdbc.getCurrentSession().getConnection(), createSQL.insertTableSQL(userObject));
//        executeSQL(sessionManagerJdbc.getCurrentSession().getConnection(), createSQL.updateTableSQL(userObject));
        sessionManagerJdbc.commitSession();
        logger.info("Значения успешно сохранены");
    }

    @Override
    public void delete(T userObject) throws SQLException {
        logger.info("Удаляю значения для: {}", userObject);
        executeSQL(sessionManagerJdbc.getCurrentSession().getConnection(), createSQL.deleteTableSQL(userObject));
        logger.info("Значения успешно удалены");
    }

    @Override
    public Optional<T> get(T userObject) throws SQLException {
        logger.info("Выбрать значения для: {}", userObject);
        //userObject = createSQL.selectTableSQL(userObject);

        DbExecutor<T> executor = new DbExecutor<>();

        Optional<T> tmpObject = executor.selectRecord(
                sessionManagerJdbc.getCurrentSession().getConnection(),
                createSQL.selectTableSQL(userObject),
                new FieldsForDbImpl().getFieldsAndValues(userObject).stream().
                        filter(x -> x.getKey() == true).
                        limit(1).
                        collect(Collectors.toList()).get(0).getValueStr(),
                resultSet -> {
                    Class cl = null;

                    try {
                        cl = Class.forName(userObject.getClass().getName());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        return null;
                    }

                    Object obj = null;

                    try {
                        obj = cl.getConstructor().newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                        return null;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return null;
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                        return null;
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                        return null;
                    }

                    for (var field : new FieldsForDbImpl().getFieldsAndValues(userObject)) {

                        String methodName = field.getNameInClass().substring(0, 1).toUpperCase() +
                                field.getNameInClass().substring(1);

                        try {
                            cl.getMethod("set" + methodName,
                                    new Class[]{cl.getMethod("get" + methodName).getReturnType()}).
                                    invoke(obj, field.getValueStr());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            return null;
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                            return null;
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }

                    return null;

                });

        logger.info("Значения успешно выбраны");
        return tmpObject;
    }

             /*
            DbExecutor<T> executor = new DbExecutor<>();
            long userId = executor.insertRecord(connection, "insert into user(name) values (?)", Collections.singletonList("testUserName"));
            logger.info("created user:{}", userId);
            connection.commit();

            Optional<T> user = executor.selectRecord(connection, "select id, name from user where id  = ?", userId, resultSet -> {
                try {
                    if (resultSet.next()) {
                        return new User(resultSet.getLong("id"), resultSet.getString("name"));
                    }
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
                return null;
            });
            System.out.println(user);
             */


//        createSQL.insertTableSQL(userObject);
//        createSQL.updateTableSQL(userObject);


    private void executeSQL(Connection connection, String sql) throws SQLException {
        logger.info("Выполняю SQL:{}", sql);
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.executeUpdate();
        }
    }


/*
  @Override
  public long saveUser(User user) {

    try (SessionManager sessionManager = userDao.getSessionManager()) {
      sessionManager.beginSession();
      try {
        long userId = userDao.saveUser(user);
        sessionManager.commitSession();

        logger.info("created user: {}", userId);
        return userId;
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
        sessionManager.rollbackSession();
        throw new DbServiceException(e);
      }
    }
  }

  @Override
  public Optional<User> getUser(long id) {
    try (SessionManager sessionManager = userDao.getSessionManager()) {
      sessionManager.beginSession();
      try {
        Optional<User> userOptional = userDao.findById(id);

        logger.info("user: {}", userOptional.orElse(null));
        return userOptional;
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
        sessionManager.rollbackSession();
      }
      return Optional.empty();
    }
  }
   */


}
