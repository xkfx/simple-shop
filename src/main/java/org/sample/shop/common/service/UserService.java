package org.sample.shop.common.service;

import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.User;

public interface UserService {

    ServiceResult<User> register(int type, String username, String password);

    ServiceResult getUser(String username, String password);
}
