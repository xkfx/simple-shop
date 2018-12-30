package org.sample.shop.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.dao.OrderDAO;
import org.sample.shop.dao.impl.OrderDAOImpl;
import org.sample.shop.db.connmanager.ConnectionProxy;
import org.sample.shop.entity.Order;
import org.sample.shop.enums.business.BusinessCode;
import org.sample.shop.exception.DaoException;
import org.sample.shop.service.OrderService;
import org.sample.shop.service.ServiceResult;

import javax.servlet.http.HttpSession;

import java.util.List;

import static org.sample.shop.enums.business.BusinessCode.*;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger();
    private final OrderDAO orderDAO = OrderDAOImpl.INSTANCE;

    @Override
    public ServiceResult<List<Order>> listOrders(long uid) {
        try {
            return new ServiceResult<>(ORDER_LIST_SUCCESS, orderDAO.listByUid(uid));
        } catch (DaoException e) {
            return new ServiceResult<>(ORDER_LIST_FAIL);
        } finally {
            ConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<Order> placeOrder(long uid, HttpSession session) {
        double total = 0;
        Order order = new Order(uid, total);
        orderDAO.saveOrder(order);
        return null;
    }

    @Override
    public ServiceResult addToCart(HttpSession session) {

        return null;
    }

    @Override
    public ServiceResult removeFromCart(HttpSession session) {
        return null;
    }

    @Override
    public ServiceResult viewCart(HttpSession session) {
        return null;
    }
}
