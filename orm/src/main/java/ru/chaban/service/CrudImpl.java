package ru.chaban.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chaban.h2.DataSourceH2;
import ru.chaban.jdbc.DbExecutor;
import ru.chaban.jdbc.ExecutorDemo;
import ru.chaban.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.*;
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

        logger.info("Запрос в БД: {}", createSQL.selectTableSQL(userObject));

        Optional<T> tmpObject = executor.selectRecord(
                sessionManagerJdbc.getCurrentSession().getConnection(),
                createSQL.selectTableSQL(userObject),
                new FieldsForDbImpl().getFieldsAndValues(userObject).stream().
                        filter(x -> x.getKey() == true).
                        limit(1).
                        collect(Collectors.toList()).get(0).getValueStr(),
                resultSet -> (T) fillAttr(resultSet, userObject)
        );

        logger.info("Значения успешно выбраны: {}", tmpObject.get());
        return tmpObject;
    }

    Object fillAttr(ResultSet resultSet, T userObject) {
        try {
            if (resultSet.next()) {
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
                for (var field : new FieldsForDbImpl().getFieldsWithoutValues(userObject)) {

                    try {
                        int i = resultSet.findColumn(field.getNameLowCase());
                    } catch (SQLSyntaxErrorException e) {
                        continue;
                    }

                    String methodName = field.getNameInClass().substring(0, 1).toUpperCase() +
                            field.getNameInClass().substring(1);

                    try {
                        if (field.getType().contains("int")) {
                            try {
                                logger.info("Реквизит/Значение: {}", methodName + "/" +
                                        ((Integer) resultSet.getInt(field.getNameInClass())).intValue());
                                cl.getMethod("set" + methodName,
                                        new Class[]{cl.getMethod("get" + methodName).getReturnType()}).
                                        invoke(obj, ((Integer) resultSet.getInt(field.getNameInClass())).intValue());
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        } else if (field.getType().contains("long")) {
                            try {
                                logger.info("Реквизит/Значение: {}", methodName + "/" +
                                        ((Long) resultSet.getLong(field.getNameInClass())).longValue());
                                cl.getMethod("set" + methodName,
                                        new Class[]{cl.getMethod("get" + methodName).getReturnType()}).
                                        invoke(obj, ((Long) resultSet.getLong(field.getNameInClass())).longValue());
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        } else {
                            try {
                                if (resultSet.getObject(field.getNameInClass()).getClass().getName().contains("BigDecimal")) {
                                    logger.info("Реквизит/Значение: {}", methodName + "/" +
                                            ((BigDecimal) (resultSet.getObject(field.getNameInClass()))).longValue());
                                    cl.getMethod("set" + methodName,
                                            new Class[]{cl.getMethod("get" + methodName).getReturnType()}).
                                            invoke(obj,
                                                    ((BigDecimal) (resultSet.getObject(field.getNameInClass()))).longValue()); //field.getValue()
                                } else {
                                    logger.info("Реквизит/Значение: {}", methodName + "/" +
                                            resultSet.getObject(field.getNameInClass()));
                                    cl.getMethod("set" + methodName,
                                            new Class[]{cl.getMethod("get" + methodName).getReturnType()}).
                                            invoke(obj, resultSet.getObject(field.getNameInClass())); //field.getValue()

                                }
                            } catch (SQLException throwables) {
                                logger.error("Проблема для реквизита: {}", methodName);
                                throwables.printStackTrace();
                            }
                        }
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
                return (T) obj;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
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
