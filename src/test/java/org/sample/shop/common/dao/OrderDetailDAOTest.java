package org.sample.shop.common.dao;

import org.junit.Test;
import org.sample.shop.common.dao.impl.OrderDetailDAOImpl;
import org.sample.shop.common.db.connmanager.ConnectionProxy;
import org.sample.shop.common.entity.OrderDetail;
import org.sample.shop.common.enums.entitystatus.impl.OrderDetailStatus;

import java.util.List;

public class OrderDetailDAOTest {

    private final OrderDetailDAO dao = OrderDetailDAOImpl.INSTANCE;

    @Test
    public void getByOrderId() throws Exception {
        List<OrderDetail> list = dao.listByOrderId(1000L);
        System.out.println(list);

        ConnectionProxy.close();
    }

    @Test
    public void getByUid() throws Exception {
        List<OrderDetail> list = dao.listByUid(1000L);
        System.out.println(list);

        ConnectionProxy.close();
    }

    @Test
    public void updateById() {
        OrderDetail detail = new OrderDetail(1001L, OrderDetailStatus.COMPLETED.getCode());
        dao.updateById(detail);

        ConnectionProxy.close();
    }
}