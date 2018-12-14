package org.sample.shop.dao;

import org.junit.Test;
import org.sample.shop.dao.impl.SimpleUserDAOImpl;
import org.sample.shop.entity.SimpleUser;
import org.sample.shop.exception.DaoException;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimpleUserDAOTest {

    private SimpleUserDAO simpleUserDAO = SimpleUserDAOImpl.INSTANCE;

    @Test
    public void saveUser() {
        try {
            int i = simpleUserDAO.saveUser(new SimpleUser(1, "admin2345", "123"));
            assertEquals(1, i);
        } catch (DaoException e) {
            assertTrue(e.getCause() instanceof SQLIntegrityConstraintViolationException);
        } finally {
            try {
                ConnectionProxy.close();
            } catch (DaoException e) {
                throw e; // 宕机异常。
            }
        }
    }

    @Test
    public void getByUsername() {
        System.out.println(simpleUserDAO.getByUsername("admin234"));
        ConnectionProxy.close();
    }
}
