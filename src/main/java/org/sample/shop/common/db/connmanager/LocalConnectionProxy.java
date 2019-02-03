package org.sample.shop.common.db.connmanager;

import org.sample.shop.common.db.datasource.HikariCPUtils;
import org.sample.shop.common.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * 将Service层可能直接调用的方法
 * 的受查异常重新抛出为运行时异常。
 * 统一在Service层捕获。
 */
public class LocalConnectionProxy {

    private static final ThreadLocal<Connection> LocalConnectionHolder = new ThreadLocal<>();

    private LocalConnectionProxy() {
        // Exists to defeat instantiation
    }

    public static Connection getConn() throws SQLException {
        Connection conn = LocalConnectionHolder.get();
        if (conn == null || conn.isClosed()) {
            conn = ConnectionFactory.getConnection();
            LocalConnectionHolder.set(conn);
        }
        return conn;
    }

    public static void setTransactionIsolation(int level) {
        try {
            LocalConnectionProxy.getConn().setTransactionIsolation(level);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static void commit() {
        try {
            LocalConnectionProxy.getConn().commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static void rollback() {
        try {
            LocalConnectionProxy.getConn().rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static void close() {
        if (LocalConnectionHolder.get() == null) return;
        try {
            LocalConnectionProxy.getConn().close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        LocalConnectionHolder.remove();
    }

    private static class ConnectionFactory {
        private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

        static {
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                throw new DaoException(e);
            }
        }

        private ConnectionFactory() {
            // Exists to defeat instantiation
        }

        private static Connection getConnection() throws SQLException {
            return HikariCPUtils.getConnection();
        }
    }
}