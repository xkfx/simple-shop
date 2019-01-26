package org.sample.shop.common.service;

import org.junit.Test;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.User;
import org.sample.shop.common.service.impl.UserServiceImpl;

public class UserServiceTest {

    private final UserService userService = new UserServiceImpl();

    @Test
    public void saveUser() {
        ServiceResult<User> result = userService.saveUser(0, "TEST_TRANSACTION", "123");
        System.out.println(result);
    }

    @Test
    public void getUser() {
        ServiceResult<User> result = userService.getUser("TEST_TRANSACTION", "123");
        System.out.println(result);
    }
}
