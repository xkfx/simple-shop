package org.sample.shop.common.util;

import org.sample.shop.common.db.connmanager.LocalConnectionProxy;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.exception.DaoException;

/**
 * 消除Service层模板代码
 */
public class ServiceUtils {

    public interface DaoOperation<T> {
        ServiceResult<T> execute();
    }

    /**
     * 该调用可能抛出运行时异常：DAOException
     */
    public static <T> ServiceResult<T> daoOperation(DaoOperation<T> operation, int level) {
        try {
            LocalConnectionProxy.setTransactionIsolation(level);
            ServiceResult<T> result = operation.execute();
            LocalConnectionProxy.commit();
            return result;
        } catch (Exception e) {
            LocalConnectionProxy.rollback();
            throw new DaoException(e);
        } finally {
            LocalConnectionProxy.close();
        }
    }
}
