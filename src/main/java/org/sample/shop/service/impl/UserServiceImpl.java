package org.sample.shop.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.dao.UserDAO;
import org.sample.shop.dao.impl.UserDAOImpl;
import org.sample.shop.db.connmanager.ConnectionProxy;
import org.sample.shop.entity.User;
import org.sample.shop.exception.DaoException;
import org.sample.shop.service.ServiceResult;
import org.sample.shop.service.UserService;

import static org.sample.shop.enums.business.BusinessCode.*;

public enum UserServiceImpl implements UserService {

    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger();
    private final UserDAO userDAO = UserDAOImpl.INSTANCE;

    @Override
    public ServiceResult<User> register(int type, String username, String password) {
        User user = new User(type, username, password);
        try {
            userDAO.saveUser(user);
            return new ServiceResult<>(REGISTER_SUCCESS, userDAO.getByUsername(username));
        } catch (DaoException e) {
            return new ServiceResult<>(USER_EXISTED);
        } finally {
            ConnectionProxy.close(); // 异常内部处理了。
        }
    }

    @Override
    public ServiceResult login(String username, String password) {
        // TODO
        User user = userDAO.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return new ServiceResult<>(LOGIN_SUCCESS, user);
        } else {
            return new ServiceResult<>(LOGIN_FAIL);
        }
    }
}
