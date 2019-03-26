package org.sample.shop.common.dao.impl;

import org.sample.shop.common.dao.ItemDAO;
import org.sample.shop.common.db.QueryRunnerProxy;
import org.sample.shop.common.dataobject.ItemDO;

import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public List<ItemDO> listByUidAndStatus(Long uid, int status, int start, int offset) {
        return QueryRunnerProxy.query("item_listByUidAndStatus", uid, status, start, offset);
    }

    @Override
    public void saveItem(ItemDO itemDO) {
        QueryRunnerProxy.update("item_saveItem", itemDO.getUserId(), itemDO.getName(), itemDO.getPrice(),
                itemDO.getStatus(), itemDO.getQuantity());
    }

    @Override
    public int removeById(Long id) {
        return QueryRunnerProxy.update("item_removeById", id);
    }

    @Override
    public int updateById(ItemDO itemDO) {
        // 如果有其它连接在操作item表（例如管理员登陆Oracle数据库），该调用会阻塞！！！
        return QueryRunnerProxy.update("item_updateById", itemDO.getName(), itemDO.getPrice(), itemDO.getStatus(), itemDO.getQuantity(), itemDO.getId());
    }

    @Override
    public ItemDO getById(Long id) {
        List list = QueryRunnerProxy.query("item_getById", id);
        if (list.size() > 0) {
            return (ItemDO) list.get(0);
        }
        return null;
    }
}
