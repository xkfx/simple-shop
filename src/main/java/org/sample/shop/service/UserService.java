package org.sample.shop.service;

import org.sample.shop.dto.ServiceResult;
import org.sample.shop.entity.User;

public interface UserService {

    ServiceResult<User> register(int type, String username, String password);

    ServiceResult getUser(String username, String password);
}
