package org.sample.shop.common.db.queryrunner;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.sample.shop.common.db.connmanager.ConnectionFactory;
import org.sample.shop.common.exception.DaoException;

import java.sql.SQLException;
import java.util.Map;

import static org.sample.shop.common.db.queryrunner.RsHandlers.mapHandler;

/**
 * 业务相关，封装DAO层通用代码
 * 不会关闭connection！！！
 */
public class QueryRunnerProxy {

    private QueryRunnerProxy() {
        // Exists to defeat instantiation
    }

    private static QueryRunner runner = new QueryRunner();

    public static int update(String sql, Object... param) {
        int updates = 0;
        try {
            updates = runner.update(ConnectionFactory.getConnection(), sql, param);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return updates;
    }

    public static <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) {
        T result;
        try {
            result = runner.query(ConnectionFactory.getConnection(), sql, rsh, params);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public static Long getKey(String sql) {
        Long result;
        try {
            Map found = runner.query(ConnectionFactory.getConnection(), sql, mapHandler);
            result = Long.parseLong(found.get("CURRVAL").toString());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public static Double getDouble(String sql, String colName, Object... params) {
        Double result;
        try {
            Map found = runner.query(ConnectionFactory.getConnection(), sql, mapHandler, params);
            result = Double.parseDouble(found.get(colName).toString());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}