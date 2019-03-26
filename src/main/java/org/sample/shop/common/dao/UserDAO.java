package org.sample.shop.common.dao;

import org.sample.shop.common.dataobject.User;

public interface UserDAO {

    User getByUsername(String username);

    User getUser(String username, String password);

    int saveUser(User user);

    int updateById(User user);
}
