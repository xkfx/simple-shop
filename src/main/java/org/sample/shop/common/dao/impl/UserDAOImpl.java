package org.sample.shop.common.dao.impl;

import org.sample.shop.common.dao.UserDAO;
import org.sample.shop.common.db.queryrunner.QueryRunnerProxy;
import org.sample.shop.common.db.queryrunner.RsHandlers;
import org.sample.shop.common.db.queryrunner.Statements;
import org.sample.shop.common.entity.User;

/**
 * 该类方法统一抛出DaoException！！！
 */
public class UserDAOImpl implements UserDAO {

    @Override
    public User getByUsername(String username) {
        return QueryRunnerProxy.query(Statements.USER_GET_BY_USERNAME, RsHandlers.USER, username);
    }

    @Override
    public User getUser(String username, String password) {
        return QueryRunnerProxy.query(Statements.USER_GET, RsHandlers.USER, username, password);
    }

    @Override
    public int saveUser(User user) {
        return QueryRunnerProxy.update(Statements.USER_SAVE_USER, user.getType(), user.getUsername(), user.getPassword());
    }

    @Override
    public int updateById(User user) {
        return QueryRunnerProxy.update(Statements.USER_UPDATE_BY_ID, user.getPassword(), user.getId());
    }
}
