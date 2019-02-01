package org.sample.shop.common.dao;

import org.junit.Test;
import org.sample.shop.common.dao.impl.UserDAOImpl;
import org.sample.shop.common.db.connmanager.ConnectionProxy;
import org.sample.shop.common.entity.User;

import static org.junit.Assert.assertEquals;

public class UserDAOTest {

    private UserDAO dao = new UserDAOImpl();

    private static String randomStr() {
        return Math.random() + "";
    }

    private static User randomUser() {
        return new User(1, randomStr(), "123");
    }

    @Test
    public void saveUser() {
        int i = dao.saveUser(randomUser());
        assertEquals(1, i);
    }

    @Test
    public void getByUsername() {
        System.out.println(dao.getByUsername("test_merchant"));
        ConnectionProxy.close();
    }

    @Test
    public void getUser() {
        System.out.println(dao.getUser("test_merchant", "1234"));
        ConnectionProxy.close();
    }

    @Test
    public void updateById() {
        User user = new User();
        user.setId(1000L);
        user.setPassword("999999");
        dao.updateById(user);

        ConnectionProxy.close();
    }
}
