package org.sample.shop.service;

import org.sample.shop.entity.Order;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface OrderService {
    /**
     * 订单查询
     */
    ServiceResult<List<Order>> listOrders(long uid);

    /**
     * 下单：创建订单以及订单项。
     * 购物车存在内存里
     * 参数：购物车+uid
     */
    ServiceResult<Order> placeOrder(long uid, HttpSession session);

    ServiceResult addToCart(HttpSession session);

    ServiceResult removeFromCart(HttpSession session);

    ServiceResult viewCart(HttpSession session);
}
