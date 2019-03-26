package org.sample.shop.customer.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.common.dao.CartDAO;
import org.sample.shop.common.dao.OrderDAO;
import org.sample.shop.common.dao.OrderDetailDAO;
import org.sample.shop.common.dao.impl.CartDAOImpl;
import org.sample.shop.common.dao.impl.OrderDAOImpl;
import org.sample.shop.common.dao.impl.OrderDetailDAOImpl;
import org.sample.shop.common.db.connmanager.LocalConnectionProxy;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.dataobject.Order;
import org.sample.shop.common.dataobject.OrderDetail;
import org.sample.shop.common.exception.DaoException;
import org.sample.shop.customer.service.OrderService;

import java.util.List;

import static org.sample.shop.common.enums.business.BusinessCode.*;
import static org.sample.shop.common.enums.entitystatus.impl.OrderDetailStatus.NOT_DELIVERED;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final CartDAO cartDAO = new CartDAOImpl();
    private final OrderDetailDAO detailDAO = new OrderDetailDAOImpl();

    @Override
    public ServiceResult<List<Order>> listOrders(long uid) {
        try {
            return new ServiceResult<>(ORDER_LIST_SUCCESS, orderDAO.listByUid(uid));
        } catch (DaoException e) {
            return new ServiceResult<>(ORDER_LIST_FAIL);
        } finally {
            LocalConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<Order> getPreOrder(long uid) {
        try {
            return new ServiceResult<>(order_getPreOrder, cartDAO.getPreOrder(uid));
        } catch (DaoException e) {
            return new ServiceResult<>(order_getPreOrder_fail);
        } finally {
            LocalConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<Order> placeOrder(long uid) {
        try {
            // 1.查询购物车生成订单
            Order order = cartDAO.getPreOrder(uid);
            // 2.将订单存入数据库
            orderDAO.saveOrder(order);
            // 3.删除购物车内容
            cartDAO.removeAll(uid);
            return new ServiceResult<>(order_placeOrder, order);
        } catch (DaoException e) {
            return new ServiceResult<>(order_placeOrder_fail);
        } finally {
            LocalConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<Order> payOrder(long orderId) {
        try {
            // 1、拿到相应的orderdetail
            List<OrderDetail> details = detailDAO.listByOrderId(orderId);
            // 2、逐个更新到已付款的状态，并更新至数据库
            for (OrderDetail x : details) {
                x.setStatus(NOT_DELIVERED.getCode());
                detailDAO.updateById(x);
            }
            return new ServiceResult<>(order_payOrder);
        } catch (DaoException e) {
            return new ServiceResult<>(order_payOrder_fail);
        } finally {
            LocalConnectionProxy.close();
        }
    }

}
