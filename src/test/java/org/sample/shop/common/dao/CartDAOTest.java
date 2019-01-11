package org.sample.shop.common.dao;

import org.junit.Test;
import org.sample.shop.common.dao.impl.CartDAOImpl;
import org.sample.shop.common.entity.Cart;
import org.sample.shop.common.entity.Order;

import java.util.List;

public class CartDAOTest {

    CartDAO cartDAO = new CartDAOImpl();

    @Test
    public void addItem() {
        int updates = cartDAO.addItem(1000L, 11353L);
        System.out.println(updates);
    }

    @Test
    public void removeItem() {
        int updates = cartDAO.removeItem(1001L);
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
