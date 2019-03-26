package org.sample.shop.common.service;

import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.dataobject.User;

public interface UserService {

    ServiceResult<User> saveUser(int type, String username, String password);

    ServiceResult<User> getUser(String username, String password);
}
