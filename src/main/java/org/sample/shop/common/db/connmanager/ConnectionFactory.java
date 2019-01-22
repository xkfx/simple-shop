package org.sample.shop.common.db.connmanager;

import org.sample.shop.common.db.datasource.HikariCPUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 线程池版
 */
public class ConnectionFactory {

    private ConnectionFactory() {
        // Exists to defeat instantiation
    }

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final ThreadLocal<Connection> LocalConnectionHolder = new ThreadLocal<>();

    public static Connection getConnection() throws SQLException {
        Connection conn = LocalConnectionHolder.get();
        if (conn == null || conn.isClosed()) {
            conn = HikariCPUtils.getConnection();
            LocalConnectionHolder.set(conn);
        }
        return conn;
    }

    static void removeLocalConnection() {
        LocalConnectionHolder.remove();
    }

    static boolean connExisted() {
        return LocalConnectionHolder.get() != null;
    }
}
