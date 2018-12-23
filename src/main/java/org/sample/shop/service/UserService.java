package org.sample.shop.service;

import org.sample.shop.entity.User;

public interface UserService {

    ServiceResult<User> register(int type, String username, String password);

    ServiceResult login(String username, String password);
}
