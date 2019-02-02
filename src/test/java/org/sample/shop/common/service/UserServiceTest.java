package org.sample.shop.common.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

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
