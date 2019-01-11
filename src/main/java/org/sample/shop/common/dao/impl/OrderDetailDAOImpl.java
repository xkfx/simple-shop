package org.sample.shop.common.dao.impl;

import org.sample.shop.common.dao.OrderDetailDAO;
import org.sample.shop.common.db.queryrunner.QueryRunnerProxy;
import org.sample.shop.common.db.queryrunner.RsHandlers;
import org.sample.shop.common.db.queryrunner.Statements;
import org.sample.shop.common.entity.OrderDetail;

import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

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
