package org.sample.shop.common.db.connmanager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.common.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * conn.close是业务无关的，出现了异常
 * 也不会影响之前的操作，不需要通知用户，
 * 所以抛出的sqlexception内部处理。
 */
public class ConnectionProxy {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void setAutoCommit(boolean autoCommit) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static void commit() {
        try {
            Connection conn = ConnectionFactory.getConnection();
            conn.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static void rollback() {
        try {
            Connection conn = ConnectionFactory.getConnection();
            conn.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static void close() {
        if (!ConnectionFactory.connExisted()) {
            return;
        }
        try {
            ConnectionFactory.getConnection().close();
            ConnectionFactory.removeLocalConnection();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}