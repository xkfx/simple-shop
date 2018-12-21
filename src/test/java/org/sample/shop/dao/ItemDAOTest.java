package org.sample.shop.dao;

import org.junit.Test;
import org.sample.shop.dao.impl.ItemDAOImpl;
import org.sample.shop.db.connmanager.ConnectionProxy;
import org.sample.shop.entity.Item;
import org.sample.shop.enums.ItemEnum;

import java.util.List;

public class ItemDAOTest {

    private final ItemDAO itemDAO = ItemDAOImpl.INSTANCE;

    @Test
    public void saveItem() {
        Item item = new Item(1000L, "牙刷", 23.5, ItemEnum.OFF.getStatus(), 20);
        itemDAO.saveItem(item); // uid不存在会抛出异常：java.sql.SQLIntegrityConstraintViolationException: ORA-02291: 违反完整约束条件

        ConnectionProxy.close();
    }

    @Test
    public void listByUidAndStatus() {
        List<Item> items = itemDAO.listByUidAndStatus(1000L, ItemEnum.OFF.getStatus(), 0, 0);
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
        Item item = new Item(1000L, "牙刷", 23.5, ItemEnum.ON.getStatus(), 211);
        item.setId(1000L);
        itemDAO.updateById(item);

        ConnectionProxy.close();
    }
}
