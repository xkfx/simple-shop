package org.sample.shop.dao;

import org.junit.Test;
import org.sample.shop.dao.impl.TransportOrderDAOImpl;
import org.sample.shop.db.connmanager.ConnectionProxy;
import org.sample.shop.entity.TransportOrder;

import java.util.List;

import static org.junit.Assert.*;

public class TransportOrderDAOTest {

    private TransportOrderDAO dao = TransportOrderDAOImpl.INSTANCE;

    @Test
    public void saveOrder() throws Exception {
        TransportOrder order = new TransportOrder(1000L, 1000L, "加勒比", 0);
        dao.saveOrder(order);

        ConnectionProxy.close();
    }

    @Test
    public void getByUid() throws Exception {
        List<TransportOrder> list = dao.getByUid(1000L, 1, 1);
        System.out.println(list);
        ConnectionProxy.close();
    }

    @Test
    public void getByDetailId() throws Exception {
        TransportOrder order = dao.getByDetailId(1000L);
        System.out.println(order);
        ConnectionProxy.close();
    }

    @Test
    public void updateById() throws Exception {
        TransportOrder order = new TransportOrder(1000L, 1000L, "迪斯尼", 1);
        order.setId(1001L);
        dao.updateById(order);
        ConnectionProxy.close();
    }

    @Test
    public void getById() {
        TransportOrder order = dao.getById(1000L);
        System.out.println(order);
        ConnectionProxy.close();
    }
}