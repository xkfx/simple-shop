package org.sample.shop.dao.impl;

import org.sample.shop.dao.TransportOrderDAO;
import org.sample.shop.db.queryrunner.QueryRunnerProxy;
import org.sample.shop.db.queryrunner.RsHandlers;
import org.sample.shop.db.queryrunner.SQLs;
import org.sample.shop.entity.TransportOrder;

import java.util.List;

public enum TransportOrderDAOImpl implements TransportOrderDAO {

    INSTANCE;

    @Override
    public int saveOrder(TransportOrder order) {
        return QueryRunnerProxy.update(SQLs.TRANSPORT_ORDER_SAVE_ORDER, order.getUserId(), order.getDetailId(), order.getLocation(), order.getStatus());
    }

    @Override
    public List<TransportOrder> getByUid(long uid) {
        return QueryRunnerProxy.query(SQLs.TRANSPORT_ORDER_GET_BY_UID, RsHandlers.TRANSPORT_ORDER_LIST, uid);
    }

    @Override
    public TransportOrder getByDetailId(long detailId) {
        return QueryRunnerProxy.query(SQLs.TRANSPORT_ORDER_GET_BY_DETAIL_ID, RsHandlers.TRANSPORT_ORDER, detailId);
    }

    @Override
    public TransportOrder getById(long id) {
        return QueryRunnerProxy.query(SQLs.TRANSPORT_ORDER_GET_BY_ID, RsHandlers.TRANSPORT_ORDER, id);
    }

    @Override
    public int updateById(TransportOrder order) {
        return QueryRunnerProxy.update(SQLs.TRANSPORT_ORDER_UPDATE_BY_ID, order.getLocation(), order.getStatus(), order.getId());
    }
}
