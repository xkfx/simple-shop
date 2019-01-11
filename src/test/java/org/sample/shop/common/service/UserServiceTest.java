package org.sample.shop.common.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.User;
import org.sample.shop.common.service.impl.UserServiceImpl;

public class UserServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private final UserService userService = new UserServiceImpl();

    @Test
    public void register() {
        ServiceResult<User> result = userService.register(0, "a31321", "!23123");
        LOGGER.debug(result);
    }
}
