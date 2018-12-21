package org.sample.shop.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.db.connmanager.ConnectionProxy;
import org.sample.shop.dao.UserDAO;
import org.sample.shop.dao.impl.UserDAOImpl;
import org.sample.shop.dto.ServiceResult;
import org.sample.shop.entity.User;
import org.sample.shop.enums.ServiceEnum;
import org.sample.shop.exception.DaoException;
import org.sample.shop.service.UserService;

import java.sql.SQLIntegrityConstraintViolationException;

public enum UserServiceImpl implements UserService {

    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger();
    private final UserDAO userDAO = UserDAOImpl.INSTANCE;

    @Override
    public ServiceResult<User> register(int type, String username, String password) {
        int result = 0;
        User user = new User(type, username, password);
        try {
            result = userDAO.saveUser(user);
        } catch (DaoException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                // 重复注册
                return new ServiceResult<>(false, ServiceEnum.USER_EXISTED);
            }
            // 异常
            LOGGER.error("[UserService]register: An error occurred while userDAO.saveUser(user)", e);
        } finally {
            try {
                ConnectionProxy.close();
            } catch (DaoException e) {
                // 异常
                LOGGER.error("[UserService]register: An error occurred while ConnectionProxy.close()", e);
            }
        }
        if (result == 1) {
            // 注册成功
            return new ServiceResult<>(true, ServiceEnum.REGISTER_SUCCESS, user);
        } else {
            // 异常
            LOGGER.warn("[UserService]register: A strange thing happened during registration,i={},{}", result, user);
            return new ServiceResult<>(false, ServiceEnum.INNER_ERROR);
        }
    }

    @Override
    public ServiceResult login(String username, String password) {
        User user = userDAO.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return new ServiceResult<>(true, ServiceEnum.LOGIN_SUCCESS, user);
        } else {
            return new ServiceResult<Integer>(false, ServiceEnum.LOGIN_FAIL);
        }
    }
}
