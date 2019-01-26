package org.sample.shop.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.common.db.connmanager.ConnectionProxy;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.User;

import java.sql.Connection;

public class ServiceUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    public interface DaoOperation<T> {
        ServiceResult<T> execute();
    }

    public static <T> ServiceResult<T> daoOperation(DaoOperation<T> operation) {
        return ServiceUtils.daoOperation(operation, Connection.TRANSACTION_REPEATABLE_READ);
        // InnoDB的REPEATABLE_READ实际就已经达到了SQL标准的SERIALIZABLE，充分满足ACID特性

    }

    public static <T> ServiceResult<T> daoOperation(DaoOperation<T> operation, int level) {
        try {
            ConnectionProxy.setTransactionIsolation(level);
            ServiceResult<T> result = operation.execute();
            ConnectionProxy.commit();
            return result;
        } catch (Exception e) {
            LOGGER.error(e);
            ConnectionProxy.rollback();
            return ServiceResult.error();
        } finally {
            ConnectionProxy.close(); // 异常内部处理了。
        }
    }
}
