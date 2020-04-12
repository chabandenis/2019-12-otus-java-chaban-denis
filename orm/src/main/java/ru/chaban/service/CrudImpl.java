package ru.chaban.service;

import java.util.Optional;

public class CrudImpl<T> implements Crud<T> {

    private CreateSQL createSQL = new CreateSQLImpl();

    @Override
    public void save(T userObject) {
        createSQL.createTableSQL(userObject);
        createSQL.deleteTableSQL(userObject);
        createSQL.insertTableSQL(userObject);
        createSQL.updateTableSQL(userObject);


    }

    @Override
    public void delete(String id) {


    }

    @Override
    public Optional get(String id) {

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
