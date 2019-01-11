package org.sample.shop.common.dao.impl;

import org.sample.shop.common.dao.CartDAO;
import org.sample.shop.common.db.queryrunner.QueryRunnerProxy;
import org.sample.shop.common.db.queryrunner.RsHandlers;
import org.sample.shop.common.db.queryrunner.Statements;
import org.sample.shop.common.entity.Cart;
import org.sample.shop.common.entity.Order;
import org.sample.shop.common.entity.OrderDetail;

import java.util.List;

public class CartDAOImpl implements CartDAO {
    @Override
    public int addItem(Long uid, Long itemId) {
        return QueryRunnerProxy.update(Statements.cart_addItem, uid, itemId);
    }

    @Override
    public int removeItem(Long id) {
        return QueryRunnerProxy.update(Statements.cart_removeItem, id);
    }

    @Override
    public int removeAll(Long uid) {
        return QueryRunnerProxy.update(Statements.cart_removeAll, uid);
    }

    @Override
    public List<Cart> listByUid(Long uid) {
        return QueryRunnerProxy.query(Statements.cart_getByUid, RsHandlers.cart_list, uid);
    }

    @Override
    public Order getPreOrder(Long uid) {
        Order order = new Order();
        order.setUserId(uid); // 设置买家id
        // 1. 查总价
        Double total = QueryRunnerProxy.getDouble(Statements.cart_getSum, "sum", uid);
        order.setTotal(total);
        // 2. 查对应的物品清单
        List<OrderDetail> details = QueryRunnerProxy.query(Statements.cart_getPreOrder, RsHandlers.ORDER_DETAIL_LIST, uid);
        order.setDetails(details);
        return order;
    }
}
