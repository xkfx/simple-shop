package org.sample.shop.db.connmanager;

import org.sample.shop.exception.DaoException;

import java.sql.Connection;

public class ConnectionProxy {
    public static void setAutoCommit(boolean autoCommit) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(autoCommit);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public static void commit() {
        try {
            Connection conn = ConnectionFactory.getConnection();
            conn.commit();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public static void rollback() {
        try {
            Connection conn = ConnectionFactory.getConnection();
            conn.rollback();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public static void close() {
        if (!ConnectionFactory.connExisted()) {
            return;
        }
        try {
            Connection conn = ConnectionFactory.getConnection();
            conn.close();
            ConnectionFactory.removeLocalConnection();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}