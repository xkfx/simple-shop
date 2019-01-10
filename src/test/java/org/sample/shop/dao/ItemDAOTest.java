package org.sample.shop.dao;

import org.junit.Test;
import org.sample.shop.dao.impl.ItemDAOImpl;
import org.sample.shop.db.connmanager.ConnectionProxy;
import org.sample.shop.db.queryrunner.Statements;
import org.sample.shop.entity.Item;
import org.sample.shop.enums.entitystatus.impl.ItemStatus;

import java.util.List;

public class ItemDAOTest {

    private final ItemDAO itemDAO = ItemDAOImpl.INSTANCE;

    @Test
    public void saveItem() {
        double price = Math.random() * 1000;
        Item item = new Item(1002L, "牙膏", price, ItemStatus.ON_SALE.getCode(), 20);
        itemDAO.saveItem(item); // uid不存在会抛出异常：java.sql.SQLIntegrityConstraintViolationException: ORA-02291: 违反完整约束条件

        ConnectionProxy.close();
    }

    @Test
    public void listByUidAndStatus() {
        List<Item> items = itemDAO.listByUidAndStatus(1000L, ItemStatus.OFF_SALE.getCode(), 0, 5);
        System.out.println(items);

        ConnectionProxy.close();
    }

    @Test
    public void listByUidAndStatusNew() {
        List<Item> items = itemDAO.listByUidAndStatusNew(1000L, ItemStatus.OFF_SALE.getCode(), 1, 1);
        System.out.println(items);

        ConnectionProxy.close();
    }

    @Test
    public void removeById() {
        itemDAO.removeById(1051L);

        ConnectionProxy.close();
    }

    @Test
    public void updateById() {
        Item item = new Item(1000L, "牙刷", 23.5, ItemStatus.ON_SALE.getCode(), 211);
        item.setId(3000L);
        int i = itemDAO.updateById(item);
        System.out.println(i);
        ConnectionProxy.close();
    }

    @Test
    public void getById() {
        Item item = itemDAO.getById(1000L);
        System.out.println(item);
        ConnectionProxy.close();
    }
}
