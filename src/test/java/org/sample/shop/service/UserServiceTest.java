package org.sample.shop.service;

import org.junit.Test;
import org.sample.shop.dto.ServiceResult;
import org.sample.shop.entity.User;
import org.sample.shop.service.impl.UserServiceImpl;

public class UserServiceTest {

    private UserService userService = UserServiceImpl.INSTANCE;

    @Test
    public void register() {
        ServiceResult<User> result = userService.register(0, "312321", "!23123");
        System.out.println(result);
    }
}
