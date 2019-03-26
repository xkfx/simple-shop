package org.sample.shop.common.dao;

import org.sample.shop.common.dataobject.Cart;
import org.sample.shop.common.dataobject.Order;

import java.util.List;

public interface CartDAO {

    int addItem(Long uid, Long itemId);

    int removeItem(Long id);

    int removeAll(Long uid); // 清空购物车

    List<Cart> listByUid(Long uid);

    Order getPreOrder(Long uid);
}
