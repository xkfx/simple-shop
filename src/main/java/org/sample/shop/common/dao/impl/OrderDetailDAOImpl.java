package org.sample.shop.common.dao.impl;

import org.sample.shop.common.dao.OrderDetailDAO;
import org.sample.shop.common.db.QueryRunnerProxy;
import org.sample.shop.common.entity.OrderDetail;

import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public List<OrderDetail> listByOrderId(long orderId) {
        return QueryRunnerProxy.query("orderDetail_listByOrderId", orderId);
    }

    @Override
    public List<OrderDetail> listByUid(long uid) {
        return QueryRunnerProxy.query("orderDetail_listByUid", uid);
    }

    @Override
    public int updateById(OrderDetail detail) {
        return QueryRunnerProxy.update("orderDetail_updateById", detail.getStatus(), detail.getId());
    }
}
