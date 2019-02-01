package org.sample.shop.common.db;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.sample.shop.common.db.connmanager.ConnectionFactory;
import org.sample.shop.common.db.handler.HandlerBuffer;
import org.sample.shop.common.db.html.Metadata;
import org.sample.shop.common.db.html.MetadataBuffer;
import org.sample.shop.common.exception.DaoException;

public class QueryRunnerProxy {

    private QueryRunnerProxy() {
        // Exists to defeat instantiation
    }

    private static final QueryRunner RUNNER = new QueryRunner();

    public static int update(String metadataId, Object... param) {
        int updates = 0;
        try {
            Metadata metadata = MetadataBuffer.getMetadata(metadataId);
            updates = RUNNER.update(ConnectionFactory.getConnection(), metadata.getSql(), param);
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return updates;
    }

    public static <T> T query(String metadataId, Object... params) {
        T result;
        try {
            Metadata metadata = MetadataBuffer.getMetadata(metadataId);
            ResultSetHandler handler = HandlerBuffer.getHandler(metadata.getType());
            if (handler != null) {
                result = RUNNER.query(ConnectionFactory.getConnection(), metadata.getSql(), (ResultSetHandler<T>) handler, params);
            } else {
                throw new DaoException("ResultSet Handler did not exist");
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }
}
