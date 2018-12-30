package org.sample.shop.dao;

import org.junit.Test;
import org.sample.shop.dao.impl.OrderDAOImpl;
import org.sample.shop.db.connmanager.ConnectionProxy;
import org.sample.shop.entity.Order;
import org.sample.shop.entity.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOTest {

    private final OrderDAO dao = OrderDAOImpl.INSTANCE;

    @Test
    public void saveOrder() {
        Order order = new Order(1000L, 3210);
        List<OrderDetail> details = new ArrayList<>();
        OrderDetail first = new OrderDetail(1000L, 1000L, 1000L, 30, 22.3, 0);
        OrderDetail second = new OrderDetail(1000L, 1001L, 1000L, 30, 12.2, 0);
        details.add(first);
        details.add(second);
        order.setDetails(details);
        int i = dao.saveOrder(order);
        System.out.println(i);

        ConnectionProxy.close();
    }

    @Test
    public void getByUid() {
        List<Order> order = dao.listByUid(1000L);
        System.out.println(order);

        ConnectionProxy.close();
    }
}
