package org.sample.shop.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.dao.OrderDetailDAO;
import org.sample.shop.dao.impl.OrderDetailDAOImpl;
import org.sample.shop.db.connmanager.ConnectionProxy;
import org.sample.shop.entity.OrderDetail;
import org.sample.shop.exception.DaoException;
import org.sample.shop.service.OrderDetailService;
import org.sample.shop.service.ServiceResult;

import java.util.List;

import static org.sample.shop.enums.business.BusinessCode.*;
import static org.sample.shop.enums.entitystatus.impl.OrderDetailStatus.*;

public class OrderDetailServiceImpl implements OrderDetailService {

    private static final Logger LOGGER = LogManager.getLogger();
    private final OrderDetailDAO detailDAO = OrderDetailDAOImpl.INSTANCE;

    @Override
    public ServiceResult<List<OrderDetail>> getByMerchantId(long uid) {
        try {
            return new ServiceResult<>(DETAIL_LIST_SUCCESS, detailDAO.listByUid(uid));
        } catch (DaoException e) {
            return new ServiceResult<>(DETAIL_LIST_FAIL);
        } finally {
            ConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<List<OrderDetail>> getByOrderId(long oid) {
        try {
            return new ServiceResult<>(DETAIL_LIST_SUCCESS, detailDAO.listByOrderId(oid));
        } catch (DaoException e) {
            return new ServiceResult<>(DETAIL_LIST_FAIL);
        } finally {
            ConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<OrderDetail> pay(long id) {
        // 因为DAO层只是根据id改状态，其它属性并不会影响。
        try {
            detailDAO.updateById(new OrderDetail(id, NOT_DELIVERED.getCode()));
            return new ServiceResult<>(DETAIL_UPDATE_SUCCESS);
        } catch (DaoException e) {
            return new ServiceResult<>(DETAIL_UPDATE_FAIL);
        } finally {
            ConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<OrderDetail> completed(long id) {
        try {
            detailDAO.updateById(new OrderDetail(id, COMPLETED.getCode()));
            return new ServiceResult<>(DETAIL_UPDATE_SUCCESS);
        } catch (DaoException e) {
            return new ServiceResult<>(DETAIL_UPDATE_FAIL);
        } finally {
            ConnectionProxy.close();
        }
    }
}
