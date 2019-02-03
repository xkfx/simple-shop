package org.sample.shop.express.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.common.dao.OrderDetailDAO;
import org.sample.shop.common.dao.TransportOrderDAO;
import org.sample.shop.common.dao.impl.OrderDetailDAOImpl;
import org.sample.shop.common.dao.impl.TransportOrderDAOImpl;
import org.sample.shop.common.db.connmanager.LocalConnectionProxy;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.TransportOrder;
import org.sample.shop.common.enums.entitystatus.impl.TransportStatus;
import org.sample.shop.common.exception.DaoException;
import org.sample.shop.express.service.TransportService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.sample.shop.common.enums.business.BusinessCode.*;

public class TransportServiceImpl implements TransportService {

    private TransportOrderDAO transportOrderDAO = new TransportOrderDAOImpl();

    @Override
    public ServiceResult<TransportOrder> createNew(long userId, long detailId, String location) {
        try {
            TransportOrder order = new TransportOrder(userId, detailId, location, TransportStatus.WAITING.getCode());
            transportOrderDAO.saveOrder(order);
            return new ServiceResult<>(TRANSPORT_CREATE_SUCCESS, order);
        } catch (DaoException e) {
            return new ServiceResult<>(TRANSPORT_CREATE_FAIL);
        } finally {
            LocalConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<List<TransportOrder>> getByUid(long uid,  int start, int offset) {
        try {
            List<TransportOrder> list = transportOrderDAO.getByUid(uid, start, offset);
            return new ServiceResult<>(TRANSPORT_GET_SUCCESS, list);
        } catch (DaoException e) {
            return new ServiceResult<>(TRANSPORT_GET_FAIL);
        } finally {
            LocalConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<TransportOrder> getByDetailId(long detailId) {
        try {
            TransportOrder order = transportOrderDAO.getByDetailId(detailId);
            return new ServiceResult<>(TRANSPORT_GET_SUCCESS, order);
        } catch (DaoException e) {
            return new ServiceResult<>(TRANSPORT_GET_FAIL);
        } finally {
            LocalConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<TransportOrder> updateLocation(long id, String location) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("location", location);
        return abstractUpdate(id, parameters);
    }

    @Override
    public ServiceResult<TransportOrder> updateToWait(long id) {
        return abstractUpdateStatus(id, TransportStatus.WAITING);
        // 创建的时候就是这个状态，基本不调用
    }

    @Override
    public ServiceResult<TransportOrder> updateToTravel(long id) {
        return abstractUpdateStatus(id, TransportStatus.TRAVELLING);
        // 。。。。
    }

    @Override
    public ServiceResult<TransportOrder> updateToDeliver(long id) {
        return abstractUpdateStatus(id, TransportStatus.DELIVERING);
    }

    @Override
    public ServiceResult<TransportOrder> finishOrder(long id) {
        return abstractUpdateStatus(id, TransportStatus.FINISH);
    }

    private ServiceResult<TransportOrder> abstractUpdateStatus(Long id, TransportStatus status) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("status", status.getCode());
        return abstractUpdate(id, parameters);
    }

    private ServiceResult<TransportOrder> abstractUpdate(Long id, Map<String, Object> parameters) {
        try {
            TransportOrder order = transportOrderDAO.getById(id);
            if (order != null) {
                final Object location = parameters.get("location");
                final Object status = parameters.get("status");
                order.setLocation(location == null ? order.getLocation() : String.valueOf(location));
                order.setStatus(status == null ? order.getStatus() : (int) status);
                if (transportOrderDAO.updateById(order) == 1) {
                    return new ServiceResult<>(TRANSPORT_UPDATE_SUCCESS, order);
                } else {
                    return new ServiceResult<>(TRANSPORT_UPDATE_FAIL);
                }
            } else {
                return new ServiceResult<>(TRANSPORT_EMPTY);
            }
        } catch (DaoException e) {
            return new ServiceResult<>(INNER_ERROR);
        } finally {
            LocalConnectionProxy.close();
        }
    }
}
