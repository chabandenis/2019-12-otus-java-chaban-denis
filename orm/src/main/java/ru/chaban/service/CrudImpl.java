package ru.chaban.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chaban.jdbc.ExecutorDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class CrudImpl<T> implements Crud<T> {
    private static final String URL = "jdbc:h2:mem:";
    private static Logger logger = LoggerFactory.getLogger(ExecutorDemo.class);
    private static CreateSQL createSQL = new CreateSQLImpl();

    @Override
    public void save(T userObject) throws SQLException {
        ExecutorDemo demo = new ExecutorDemo();

        try (Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(false);
            createTable(connection, createSQL.createTableSQL(userObject));

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
        }

        createSQL.insertTableSQL(userObject);
        createSQL.updateTableSQL(userObject);

    }

    private void createTable(Connection connection, String sql) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.executeUpdate();
        }
    }


    @Override
    public void delete(T userObject) {
        createSQL.deleteTableSQL(userObject);

    }

    @Override
    public Optional get(T userObject) {
        createSQL.selectTableSQL(userObject);
        return Optional.empty();
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
