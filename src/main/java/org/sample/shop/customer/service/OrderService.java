package org.sample.shop.customer.service;

import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.Order;

import java.util.List;

public interface OrderService {
    /**
     * 订单查询
     */
    ServiceResult<List<Order>> listOrders(long uid);

    ServiceResult<Order> getPreOrder(long uid);

    ServiceResult<Order> placeOrder(long uid);

    ServiceResult<Order> payOrder(long orderId);
}
