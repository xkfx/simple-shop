package org.sample.shop.dao;

import org.sample.shop.entity.Order;

import java.util.List;

public interface OrderDAO {

    List<Order> listByUid(long uid);

    int saveOrder(Order order);
}
