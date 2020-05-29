package ru.chaban.core.dao;

import ru.chaban.core.model.User;
import ru.chaban.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface UserDao {
    Optional<User> findById(long id);

    long saveUser(User user);

    SessionManager getSessionManager();
}
