package org.sample.shop.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.sample.shop.entity.User;
import org.sample.shop.service.impl.UserServiceImpl;

public class UserServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private final UserService userService = UserServiceImpl.INSTANCE;

    @Test
    public void register() {
        ServiceResult<User> result = userService.register(0, "a31321", "!23123");
        LOGGER.debug(result);
    }
}
