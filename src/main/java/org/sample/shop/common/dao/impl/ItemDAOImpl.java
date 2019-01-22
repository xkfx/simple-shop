package org.sample.shop.common.dao.impl;

import org.sample.shop.common.dao.ItemDAO;
import org.sample.shop.common.db.QueryRunnerProxy;
import org.sample.shop.common.entity.Item;

import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public List<Item> listByUidAndStatus(Long uid, int status, int start, int offset) {
        return QueryRunnerProxy.query("item_listByUidAndStatus", uid, status, start, offset);
    }

    @Override
    public void saveItem(Item item) {
        QueryRunnerProxy.update("item_saveItem", item.getUserId(), item.getName(), item.getPrice(),
                item.getStatus(), item.getQuantity());
    }

    @Override
    public int removeById(Long id) {
        return QueryRunnerProxy.update("item_removeById", id);
    }

    @Override
    public int updateById(Item item) {
        // 如果有其它连接在操作item表（例如管理员登陆Oracle数据库），该调用会阻塞！！！
        return QueryRunnerProxy.update("item_updateById", item.getName(), item.getPrice(), item.getStatus(), item.getQuantity(), item.getId());
    }

    @Override
    public Item getById(Long id) {
        List list = QueryRunnerProxy.query("item_getById", id);
        if (list.size() > 0) {
            return (Item) list.get(0);
        }
        return null;
    }
}
