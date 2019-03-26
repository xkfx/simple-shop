package org.sample.shop.common.dao.impl;

import org.sample.shop.common.dao.UserDAO;
import org.sample.shop.common.db.QueryRunnerProxy;
import org.sample.shop.common.dataobject.User;

import java.util.List;

/**
 * 该类方法统一抛出DaoException！！！
 */
public class UserDAOImpl implements UserDAO {

    @Override
    public User getByUsername(String username) {
        List list = QueryRunnerProxy.query("user_getByUsername", username);
        if (list.size() > 0) {
            return (User) list.get(0);
        }
        return null;
    }

    @Override
    public User getUser(String username, String password) {
        List list = QueryRunnerProxy.query("user_get", username, password);
        if (list.size() > 0) {
            return (User) list.get(0);
        }
        return null;
    }

    @Override
    public int saveUser(User user) {
        return QueryRunnerProxy.update("user_saveUser", user.getType(), user.getUsername(), user.getPassword());
    }

    @Override
    public int updateById(User user) {
        return QueryRunnerProxy.update("user_updateById", user.getPassword(), user.getId());
    }
}
