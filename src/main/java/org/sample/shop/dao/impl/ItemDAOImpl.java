package org.sample.shop.dao.impl;

import org.sample.shop.dao.ItemDAO;
import org.sample.shop.db.queryrunner.QueryRunnerProxy;
import org.sample.shop.db.queryrunner.RsHandlers;
import org.sample.shop.db.queryrunner.SQLs;
import org.sample.shop.entity.Item;

import java.util.List;

public enum ItemDAOImpl implements ItemDAO {

    INSTANCE;

    @Override
    public List<Item> listByUidAndStatus(Long uid, int status, int curPage, int pageSize) {
        // TODO 分页
        return QueryRunnerProxy.query(SQLs.ITEM_LIST_BY_UID_AND_STATUS, RsHandlers.ITEM_LIST, uid, status);
    }

    @Override
    public int saveItem(Item item) {
        return QueryRunnerProxy.update(SQLs.ITEM_SAVE_ITEM, item.getUid(), item.getName(), item.getPrice(),
                item.getStatus(), item.getQuantity());
    }

    @Override
    public int removeById(Long id) {
        return QueryRunnerProxy.update(SQLs.ITEM_REMOVE_BY_ID, id);
    }

    @Override
    public int updateById(Item item) {
        // 如果有其它连接在操作item表（例如管理员登陆Oracle数据库），该调用会阻塞！！！
        return QueryRunnerProxy.update(SQLs.ITEM_UPDATE_BY_ID, item.getName(), item.getPrice(), item.getStatus(), item.getQuantity(), item.getId());
    }

    @Override
    public Item getById(Long id) {
        return QueryRunnerProxy.query(SQLs.ITEM_GET_BY_ID, RsHandlers.ITEM, id);
    }
}
