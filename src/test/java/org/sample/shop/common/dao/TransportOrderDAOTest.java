package org.sample.shop.common.dao;

import org.junit.Test;
import org.sample.shop.common.dao.impl.TransportOrderDAOImpl;
import org.sample.shop.common.db.connmanager.LocalConnectionProxy;
import org.sample.shop.common.entity.TransportOrder;

import java.util.List;

public class TransportOrderDAOTest {

    private TransportOrderDAO dao = new TransportOrderDAOImpl();

    @Test
    public void saveOrder() throws Exception {
        TransportOrder order = new TransportOrder(1000L, 1000L, "加勒比", 0);
        System.out.println(dao.saveOrder(order));
        LocalConnectionProxy.close();
    }

    @Test
    public void getByUid() throws Exception {
        List<TransportOrder> list = dao.getByUid(1000L, 0, 2);
        System.out.println(list);
        LocalConnectionProxy.close();
    }

    @Test
    public void getByDetailId() throws Exception {
        TransportOrder order = dao.getByDetailId(1L);
        System.out.println(order);
        LocalConnectionProxy.close();
    }


    @Test
    public void getById() {
        TransportOrder order = dao.getById(2L);
        System.out.println(order);
        LocalConnectionProxy.close();
    }

    @Test
    public void updateById() throws Exception {
        TransportOrder order = new TransportOrder(1000L, 1000L, "迪斯尼", 1);
        order.setId(2L);
        System.out.println(dao.updateById(order));
        LocalConnectionProxy.close();
    }
}