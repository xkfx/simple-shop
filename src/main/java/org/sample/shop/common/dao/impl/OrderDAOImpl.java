package org.sample.shop.common.dao.impl;

import org.sample.shop.common.dao.OrderDAO;
import org.sample.shop.common.db.queryrunner.QueryRunnerProxy;
import org.sample.shop.common.db.queryrunner.RsHandlers;
import org.sample.shop.common.db.queryrunner.Statements;
import org.sample.shop.common.entity.Order;
import org.sample.shop.common.entity.OrderDetail;

import java.util.List;

public enum  OrderDAOImpl implements OrderDAO {

    INSTANCE;

    @Override
    public List<Order> listByUid(long uid) {
        return QueryRunnerProxy.query(Statements.ORDER_GET_BY_UID, RsHandlers.ORDER_LIST, uid);
    }

    @Override
    public int saveOrder(Order order) {
        // 1.把order对象的数据插入到order表
        int updatesOfOrder = QueryRunnerProxy.update(Statements.ORDER_SAVE_ORDER, order.getUserId(), order.getTotal());
        // 2.获取刚才的id
        Long id = QueryRunnerProxy.getKey("select order_seq.currval from dual");
        order.setId(id); // 便于返回order对象
        // 3.把orderDetail对象的数据插入到order_detail表
        int updatesOfDetails = 0;
        for (OrderDetail x : order.getDetails()) {
            updatesOfDetails += QueryRunnerProxy.update(Statements.ORDER_SAVE_ORDER_DETAIL, id, x.getItemId(), x.getUserId(), x.getQuantity(), x.getPrice(), 0);
        }
        int expected = order.getDetails().size() + 1;
        return updatesOfDetails + updatesOfOrder == expected ? 1 : 0;
    }
}
