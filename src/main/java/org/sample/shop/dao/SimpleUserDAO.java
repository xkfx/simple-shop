package org.sample.shop.dao;

import org.sample.shop.entity.SimpleUser;

public interface SimpleUserDAO {

    SimpleUser getByUsername(String username);

    int saveUser(SimpleUser user);
}
