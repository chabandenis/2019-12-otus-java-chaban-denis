package ru.chaban.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chaban.cache.HWCache;
import ru.chaban.cache.MyCache;
import ru.chaban.cache.NoInCache;
import ru.chaban.core.dao.UserDao;
import ru.chaban.core.model.User;
import ru.chaban.core.sessionmanager.SessionManager;

import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

public class DbServiceUserImpl implements DBServiceUser {
    private static Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);
    private static Map<Long, User> mapForCache = new WeakHashMap<>();
    private static HWCache<Long, User> cache = new MyCache<Long, User>(1000000, DbServiceUserImpl.mapForCache);

    private final UserDao userDao;

    public DbServiceUserImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long saveUser(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = userDao.saveUser(user);
                cache.put(user.getId(), user);
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

    @Override
    public User getUserCache(long id) {
        try {
            return cache.get(id);
        } catch (NoInCache noInCache) {

            try (SessionManager sessionManager = userDao.getSessionManager()) {
                sessionManager.beginSession();
                try {
                    Optional<User> userOptional = userDao.findById(id);

                    logger.info("user: {}", userOptional.orElse(null));
                    return userOptional.get();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    sessionManager.rollbackSession();
                }
                return null;
            }
        }
    }
}
