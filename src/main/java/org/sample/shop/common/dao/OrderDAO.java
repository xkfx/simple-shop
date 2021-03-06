package org.sample.shop.common.dao;

import org.sample.shop.common.dataobject.Order;

import java.util.List;

public interface OrderDAO {

    List<Order> listByUid(long uid);

    int saveOrder(Order order);
}
