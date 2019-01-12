package org.sample.shop.merchant.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.common.dao.OrderDetailDAO;
import org.sample.shop.common.dao.TransportOrderDAO;
import org.sample.shop.common.dao.impl.OrderDetailDAOImpl;
import org.sample.shop.common.dao.impl.TransportOrderDAOImpl;
import org.sample.shop.common.db.connmanager.ConnectionProxy;
import org.sample.shop.common.entity.OrderDetail;
import org.sample.shop.common.entity.TransportOrder;
import org.sample.shop.common.exception.DaoException;
import org.sample.shop.merchant.service.OrderDetailService;
import org.sample.shop.common.dto.ServiceResult;

import java.util.List;

import static org.sample.shop.common.enums.business.BusinessCode.*;
import static org.sample.shop.common.enums.entitystatus.impl.OrderDetailStatus.*;

public class OrderDetailServiceImpl implements OrderDetailService {

    private static final Logger LOGGER = LogManager.getLogger();
    private final OrderDetailDAO detailDAO = new OrderDetailDAOImpl();
    private final TransportOrderDAO transportDAO = TransportOrderDAOImpl.INSTANCE;

    @Override
    public ServiceResult<List<OrderDetail>> getByMerchantId(long merchantId) {
        try {
            return new ServiceResult<>(DETAIL_LIST_SUCCESS, detailDAO.listByUid(merchantId));
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
    public ServiceResult<OrderDetail> pay(long detailId) {
        // 因为DAO层只是根据id改状态，其它属性并不会影响。
        try {
            detailDAO.updateById(new OrderDetail(detailId, NOT_DELIVERED.getCode()));
            return new ServiceResult<>(DETAIL_UPDATE_SUCCESS);
        } catch (DaoException e) {
            return new ServiceResult<>(DETAIL_UPDATE_FAIL);
        } finally {
            ConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<OrderDetail> completed(long detailId) {
        try {
            detailDAO.updateById(new OrderDetail(detailId, COMPLETED.getCode()));
            return new ServiceResult<>(DETAIL_UPDATE_SUCCESS);
        } catch (DaoException e) {
            return new ServiceResult<>(DETAIL_UPDATE_FAIL);
        } finally {
            ConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<OrderDetail> deliver(long detailId, long expressUid) {
        try {
            // 1. 更改订单明细状态
            detailDAO.updateById(new OrderDetail(detailId, DELIVERED.getCode()));
            // 2. 生成运单.. 假装transportService是第三方...而对物流系统而言该service是第三方.
            transportDAO.saveOrder(new TransportOrder(expressUid, detailId, "起点", 0));
            return new ServiceResult<>(DETAIL_UPDATE_SUCCESS);
        } catch (DaoException e) {
            return new ServiceResult<>(DETAIL_UPDATE_FAIL);
        } finally {
            ConnectionProxy.close();
        }
    }
}
