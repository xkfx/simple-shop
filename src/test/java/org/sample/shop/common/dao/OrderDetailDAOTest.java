package org.sample.shop.common.dao;

import org.junit.Test;
import org.sample.shop.common.dao.impl.OrderDetailDAOImpl;
import org.sample.shop.common.db.connmanager.LocalConnectionProxy;
import org.sample.shop.common.dataobject.OrderDetail;
import org.sample.shop.common.enums.entitystatus.impl.OrderDetailStatus;

import java.util.List;

public class OrderDetailDAOTest {

    private final OrderDetailDAO dao = new OrderDetailDAOImpl();

    @Test
    public void getByOrderId() throws Exception {
        List<OrderDetail> list = dao.listByOrderId(2L);
        System.out.println(list);

        LocalConnectionProxy.close();
    }

    @Test
    public void getByUid() throws Exception {
        List<OrderDetail> list = dao.listByUid(1000L);
        System.out.println(list);

        LocalConnectionProxy.close();
    }

    @Test
    public void updateById() {
        OrderDetail detail = new OrderDetail(1L, OrderDetailStatus.COMPLETED.getCode());
        dao.updateById(detail);

        LocalConnectionProxy.close();
    }
}