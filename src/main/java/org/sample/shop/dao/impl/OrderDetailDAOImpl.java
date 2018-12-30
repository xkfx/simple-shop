package org.sample.shop.dao.impl;

import org.sample.shop.dao.OrderDetailDAO;
import org.sample.shop.db.queryrunner.QueryRunnerProxy;
import org.sample.shop.db.queryrunner.RsHandlers;
import org.sample.shop.db.queryrunner.Statements;
import org.sample.shop.entity.OrderDetail;

import java.util.List;

public enum OrderDetailDAOImpl implements OrderDetailDAO {

    INSTANCE;

    @Override
    public List<OrderDetail> listByOrderId(long orderId) {
        return QueryRunnerProxy.query(Statements.ORDER_DETAIL_GET_BY_ORDER_ID, RsHandlers.ORDER_DETAIL_LIST, orderId);
    }

    @Override
    public List<OrderDetail> listByUid(long uid) {
        return QueryRunnerProxy.query(Statements.ORDER_DETAIL_GET_BY_UID, RsHandlers.ORDER_DETAIL_LIST, uid);
    }

    public int updateById(OrderDetail detail) {
        return QueryRunnerProxy.update(Statements.ORDER_DETAIL_UPDATE_BY_ID, detail.getStatus(), detail.getId());
    }
}
