package org.sample.shop.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.dao.ConnectionProxy;
import org.sample.shop.dao.SimpleUserDAO;
import org.sample.shop.dao.impl.SimpleUserDAOImpl;
import org.sample.shop.dto.ServiceResult;
import org.sample.shop.entity.SimpleUser;
import org.sample.shop.enums.ServiceEnum;
import org.sample.shop.exception.DaoException;
import org.sample.shop.service.SimpleUserService;

import java.sql.SQLIntegrityConstraintViolationException;

public enum SimpleUserServiceImpl implements SimpleUserService {

    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger();
    private final SimpleUserDAO userDAO = SimpleUserDAOImpl.INSTANCE;

    @Override
    public ServiceResult<SimpleUser> register(int type, String username, String password) {
        int result = 0;
        SimpleUser user = new SimpleUser(type, username, password);
        try {
            result = userDAO.saveUser(user);
        } catch (DaoException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                // 重复注册
                return new ServiceResult<SimpleUser>(false, ServiceEnum.USER_EXISTED);
            }
            // 异常
            LOGGER.error("register: An error occurred while userDAO.saveUser(user)", e);
        } finally {
            try {
                ConnectionProxy.close();
            } catch (DaoException e) {
                // 异常
                LOGGER.error("register: An error occurred while ConnectionProxy.close()", e);
            }
        }
        if (result == 1) {
            // 注册成功
            return new ServiceResult<>(true, user);
        } else {
            // 异常
            LOGGER.warn("register: A strange thing happened during registration,i={},{}", result, user);
            return new ServiceResult<SimpleUser>(false, ServiceEnum.INNER_ERROR);
        }
    }

    @Override
    public ServiceResult login(String username, String password) {
        SimpleUser user = userDAO.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return new ServiceResult<>(true, null);
        } else {
            return new ServiceResult<Integer>(false, ServiceEnum.LOGIN_FAIL);
        }
    }
}
