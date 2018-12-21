package org.sample.shop.dao;

import org.junit.Test;
import org.sample.shop.dao.impl.OrderDetailDAOImpl;
import org.sample.shop.db.connmanager.ConnectionProxy;
import org.sample.shop.entity.OrderDetail;

import java.util.List;

import static org.junit.Assert.*;

public class OrderDetailDAOTest {

    private final OrderDetailDAO dao = OrderDetailDAOImpl.INSTANCE;

    @Test
    public void getByOrderId() throws Exception {
        List<OrderDetail> list = dao.getByOrderId(1000L);
        System.out.println(list);

        ConnectionProxy.close();
    }

    @Test
    public void getByUid() throws Exception {
        List<OrderDetail> list = dao.getByUid(1000L);
        System.out.println(list);

        ConnectionProxy.close();
    }

    @Test
    public void updateById() {
        OrderDetail detail = new OrderDetail();
        detail.setId(1000L);
        detail.setStatus(9);
        dao.updateById(detail);

        ConnectionProxy.close();
    }
}