package ru.chaban.service;

import java.util.Optional;
import ru.chaban.service.Crud;

public class CrudImpl implements Crud {

  @Override
  public long save(Object userObject) {
    return 0;
  }

  @Override
  public void delete(Object userObject) {

  }

  @Override
  public Optional get(Object userObject) {
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
