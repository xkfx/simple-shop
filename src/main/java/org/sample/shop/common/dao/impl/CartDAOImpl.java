package org.sample.shop.common.dao.impl;

import org.sample.shop.common.dao.CartDAO;
import org.sample.shop.common.db.QueryRunnerProxy;
import org.sample.shop.common.dataobject.Cart;
import org.sample.shop.common.dataobject.Order;
import org.sample.shop.common.dataobject.OrderDetail;

import java.util.List;
import java.util.Map;

public class CartDAOImpl implements CartDAO {

    @Override
    public int addItem(Long uid, Long itemId) {
        return QueryRunnerProxy.update("cart_addItem", uid, itemId);
    }

    @Override
    public int removeItem(Long id) {
        return QueryRunnerProxy.update("cart_removeItem", id);
    }

    @Override
    public int removeAll(Long uid) {
        return QueryRunnerProxy.update("cart_removeAll", uid);
    }

    @Override
    public List<Cart> listByUid(Long uid) {
        return QueryRunnerProxy.query("cart_getByUid", uid);
    }

    @Override
    public Order getPreOrder(Long uid) {
        Order order = new Order();
        order.setUserId(uid); // 设置买家id
        // 1. 查总价
        List<Map> mapList = QueryRunnerProxy.query("cart_getSum", uid);
        Double total = Double.parseDouble(mapList.get(0).get("sum").toString());
        order.setTotal(total);
        // 2. 查对应的物品清单
        List<OrderDetail> details = QueryRunnerProxy.query("cart_getPreOrder", uid);
        order.setDetails(details);
        return order;
    }
}
