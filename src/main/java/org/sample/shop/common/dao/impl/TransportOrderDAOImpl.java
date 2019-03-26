package org.sample.shop.common.dao.impl;

import org.sample.shop.common.dao.TransportOrderDAO;
import org.sample.shop.common.db.QueryRunnerProxy;
import org.sample.shop.common.dataobject.TransportOrder;

import java.util.List;

public class TransportOrderDAOImpl implements TransportOrderDAO {

    @Override
    public int saveOrder(TransportOrder order) {
        return QueryRunnerProxy.update("transportOrder_saveOrder", order.getUserId(), order.getDetailId(), order.getLocation(), order.getStatus());
    }

    @Override
    public List<TransportOrder> getByUid(long uid, int start, int offset) {
        return QueryRunnerProxy.query("transportOrder_getByUid", uid, start, offset);
    }

    @Override
    public TransportOrder getByDetailId(long detailId) {
        List list = QueryRunnerProxy.query("transportOrder_getByDetailId", detailId);
        if (list.size() > 0) {
            return (TransportOrder) list.get(0);
        }
        return null;
    }

    @Override
    public TransportOrder getById(long id) {
        List list = QueryRunnerProxy.query("transportOrder_getById", id);
        if (list.size() > 0) {
            return (TransportOrder) list.get(0);
        }
        return null;
    }

    @Override
    public int updateById(TransportOrder order) {
        return QueryRunnerProxy.update("transportOrder_updateById", order.getLocation(), order.getStatus(), order.getId());
    }
}
