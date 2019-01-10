package org.sample.shop.dao;

import org.sample.shop.entity.User;

public interface UserDAO {

    User getByUsername(String username);

    User getUser(String username, String password);

    int saveUser(User user);

    int updateById(User user);
}
