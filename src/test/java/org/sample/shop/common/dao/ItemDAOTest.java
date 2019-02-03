package org.sample.shop.common.dao;

import org.junit.Test;
import org.sample.shop.common.dao.impl.ItemDAOImpl;
import org.sample.shop.common.db.connmanager.LocalConnectionProxy;
import org.sample.shop.common.entity.Item;
import org.sample.shop.common.enums.entitystatus.impl.ItemStatus;

import java.util.List;

public class ItemDAOTest {

    private ItemDAO itemDAO = new ItemDAOImpl();

    @Test
    public void saveItem() {
        double price = Math.random() * 1000;
        Item item = new Item(1000L, "牙膏", price, ItemStatus.ON_SALE.getCode(), 20);
        itemDAO.saveItem(item); // uid不存在会抛出异常：java.sqls.SQLIntegrityConstraintViolationException: ORA-02291: 违反完整约束条件

        LocalConnectionProxy.close();
    }

    @Test
    public void listByUidAndStatus() {
        List<Item> items = itemDAO.listByUidAndStatus(1001L, ItemStatus.OFF_SALE.getCode(), 0, 5);
        System.out.println(items);

        LocalConnectionProxy.close();
    }

    @Test
    public void listByUidAndStatusNew() {
        List<Item> items = itemDAO.listByUidAndStatus(1001L, ItemStatus.OFF_SALE.getCode(), 1, 1);
        System.out.println(items);

        LocalConnectionProxy.close();
    }

    @Test
    public void removeById() {
        itemDAO.removeById(1008L);

        LocalConnectionProxy.close();
    }

    @Test
    public void updateById() {
        Item item = new Item(1000L, "牙刷", 23.5, ItemStatus.ON_SALE.getCode(), 211);
        item.setId(1000L);
        int i = itemDAO.updateById(item);
        System.out.println(i);
        LocalConnectionProxy.close();
    }

    @Test
    public void getById() {
        Item item = itemDAO.getById(1000L);
        System.out.println(item);
        LocalConnectionProxy.close();
    }
}
