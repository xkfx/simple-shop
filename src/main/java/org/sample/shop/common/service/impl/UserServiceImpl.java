package org.sample.shop.common.service.impl;

import org.sample.shop.common.dao.UserDAO;
import org.sample.shop.common.dao.impl.UserDAOImpl;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.User;
import org.sample.shop.common.service.UserService;
import org.sample.shop.common.util.ServiceUtils;

import java.sql.Connection;

public class UserServiceImpl implements UserService {

    private static final String USER_EXISTED = "用户名已被注册。";
    private static final String LOGIN_FAIL = "用户名或者密码错误。";

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public ServiceResult<User> saveUser(int type, String username, String password) {
        /*
        check user.type:
        check username:
        check password:

         */
        User user = new User(type, username, password);
        return ServiceUtils.daoOperation(() -> {
            int updates = userDAO.saveUser(user);
            return updates == 0 ? ServiceResult.fail(USER_EXISTED) : ServiceResult.ok(user);
        }, Connection.TRANSACTION_READ_UNCOMMITTED);
    }

    @Override
    public ServiceResult<User> getUser(String username, String password) {
        return ServiceUtils.daoOperation(() -> {
            User user = userDAO.getUser(username, password);
            return user != null ? ServiceResult.ok(user) : new ServiceResult<>(LOGIN_FAIL);
        }, Connection.TRANSACTION_READ_COMMITTED);
    }
}
