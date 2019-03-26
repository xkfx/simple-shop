package org.sample.shop.common.dao;

import org.junit.Test;
import org.sample.shop.common.dao.impl.CartDAOImpl;
import org.sample.shop.common.dataobject.Cart;
import org.sample.shop.common.dataobject.Order;

import java.util.List;

public class CartDAOTest {

    private CartDAO cartDAO = new CartDAOImpl();

    @Test
    public void addItem() {
        int updates = cartDAO.addItem(1000L, 1000L);
        System.out.println(updates);
    }

    @Test
    public void removeItem() {
        int updates = cartDAO.removeAll(1000L);
        System.out.println(updates);
    }

    @Test
    public void getByUid() {
        List<Cart> carts = cartDAO.listByUid(1000L);
        System.out.println(carts);
    }

    @Test
    public void getPreOrder() {
        Order order = cartDAO.getPreOrder(1000L);
        System.out.println(order);
    }
}
