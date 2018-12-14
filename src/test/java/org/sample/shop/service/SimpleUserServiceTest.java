package org.sample.shop.service;

import org.junit.Test;
import org.sample.shop.dto.ServiceResult;
import org.sample.shop.entity.SimpleUser;
import org.sample.shop.service.impl.SimpleUserServiceImpl;

public class SimpleUserServiceTest {

    private SimpleUserService userService = SimpleUserServiceImpl.INSTANCE;

    @Test
    public void register() {
        ServiceResult<SimpleUser> result = userService.register(0, "312321", "!23123");
        System.out.println(result);
    }
}
