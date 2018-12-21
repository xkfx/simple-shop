package org.sample.shop.dao.impl;

import org.sample.shop.dao.OrderDAO;
import org.sample.shop.db.queryrunner.QueryRunnerProxy;
import org.sample.shop.db.queryrunner.RsHandlers;
import org.sample.shop.db.queryrunner.SQLs;
import org.sample.shop.entity.Order;
import org.sample.shop.entity.OrderDetail;

import java.util.List;

public enum  OrderDAOImpl implements OrderDAO {

    INSTANCE;

    @Override
    public List<Order> getByUid(long uid) {
        return QueryRunnerProxy.query(SQLs.ORDER_GET_BY_UID, RsHandlers.ORDER_LIST, uid);
    }

    @Override
    public int saveOrder(Order order) {
        // 1.把order对象的数据插入到order表
        int updatesOfOrder = QueryRunnerProxy.update(SQLs.ORDER_SAVE_ORDER, order.getUserId(), order.getTotal());
        // 2.把orderDetail对象的数据插入到order_detail表
        int updatesOfDetails = 0;
        for (OrderDetail x : order.getDetails()) {
            updatesOfDetails += QueryRunnerProxy.update(SQLs.ORDER_SAVE_ORDER_DETAIL, x.getOrderId(), x.getItemId(), x.getUserId(), x.getQuantity(), x.getPrice(), x.getStatus());
        }
        int expected = order.getDetails().size() + 1;
        return updatesOfDetails + updatesOfOrder == expected ? 1 : 0;
    }
}
