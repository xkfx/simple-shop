package org.sample.shop.common.dao.impl;

import org.sample.shop.common.dao.OrderDAO;
import org.sample.shop.common.db.QueryRunnerProxy;
import org.sample.shop.common.dataobject.Order;
import org.sample.shop.common.dataobject.OrderDetail;

import java.util.List;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public List<Order> listByUid(long uid) {
        return QueryRunnerProxy.query("order_listByUid", uid);
    }

    @Override
    public int saveOrder(Order order) {
        // 1.把order对象插入到order表
        int updatesOfOrder = QueryRunnerProxy.update("order_saveOrder", order.getUserId(), order.getTotal());
        // 2.获取刚才的id
        List<Map> mapList = QueryRunnerProxy.query("order_getLastInsertId");
        Long id = Long.parseLong(mapList.get(0).get("id").toString());
        order.setId(id);
        // 3.把相应的orderDetail对象插入到order_detail表
        int updatesOfDetails = 0;
        for (OrderDetail x : order.getDetails()) {
            updatesOfDetails += QueryRunnerProxy.update("order_saveOrderDetail", id, x.getItemId(), x.getUserId(), x.getQuantity(), x.getPrice(), 0);
        }
        int expected = order.getDetails().size() + 1;
        return updatesOfDetails + updatesOfOrder == expected ? 1 : 0;
    }
}
