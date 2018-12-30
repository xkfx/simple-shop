package org.sample.shop.dao.impl;

import org.sample.shop.dao.UserDAO;
import org.sample.shop.db.queryrunner.QueryRunnerProxy;
import org.sample.shop.db.queryrunner.RsHandlers;
import org.sample.shop.db.queryrunner.Statements;
import org.sample.shop.entity.User;

/**
 * 该类方法统一抛出DaoException！！！
 */
public enum UserDAOImpl implements UserDAO {

    INSTANCE;

    @Override
    public User getByUsername(String username) {
        return QueryRunnerProxy.query(Statements.USER_GET_BY_USERNAME, RsHandlers.USER, username);
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
