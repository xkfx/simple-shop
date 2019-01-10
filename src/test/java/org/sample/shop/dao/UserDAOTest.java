package org.sample.shop.dao;

import org.junit.Test;
import org.sample.shop.dao.impl.UserDAOImpl;
import org.sample.shop.db.connmanager.ConnectionProxy;
import org.sample.shop.entity.User;
import org.sample.shop.exception.DaoException;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDAOTest {

    private UserDAO dao = new UserDAOImpl();

    public static String randomStr() {
        return Math.random() + "";
    }

    public static User randomUser() {
        return new User(1, randomStr(), "123");
    }

    @Test
    public void saveUser() {
        try {
            int i = dao.saveUser(randomUser());
            assertEquals(1, i);
        } catch (DaoException e) {
            e.printStackTrace();
            assertTrue(e.getCause() instanceof SQLIntegrityConstraintViolationException);
            // TODO DBUtils会把这个具体异常泛化掉，这种写法不可行，暂时不理会，有空去补习数据库知识。
        } finally {
            try {
                ConnectionProxy.close();
            } catch (DaoException e) {
                throw e;
            }
        }
    }

    @Test
    public void getByUsername() {
        System.out.println(dao.getByUsername("admin234"));
        ConnectionProxy.close();
    }

    @Test
    public void getUser() {
        System.out.println(dao.getUser("xkfx1997", "123"));
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
