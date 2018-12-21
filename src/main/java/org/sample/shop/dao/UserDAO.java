package org.sample.shop.dao;

import org.sample.shop.entity.User;

public interface UserDAO {

    User getByUsername(String username);

    int saveUser(User user);

    int updateById(User user);
}
