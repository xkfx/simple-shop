package org.sample.shop.common.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.common.dao.UserDAO;
import org.sample.shop.common.dao.impl.UserDAOImpl;
import org.sample.shop.common.db.connmanager.ConnectionProxy;
import org.sample.shop.common.entity.User;
import org.sample.shop.common.exception.DaoException;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.service.UserService;

import static org.sample.shop.common.enums.business.BusinessCode.*;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger();
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public ServiceResult<User> register(int type, String username, String password) {
        // TODO 其实只要再拿个uid就可以了.........
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
    public ServiceResult getUser(String username, String password) {
        User user = userDAO.getUser(username, password);
        if (user != null) {
            return new ServiceResult<>(LOGIN_SUCCESS, user);
        } else {
            return new ServiceResult<>(LOGIN_FAIL);
        }
    }
}
