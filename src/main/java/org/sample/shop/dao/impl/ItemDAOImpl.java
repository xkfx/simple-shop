package org.sample.shop.dao.impl;

import org.sample.shop.dao.ItemDAO;
import org.sample.shop.db.queryrunner.QueryRunnerProxy;
import org.sample.shop.db.queryrunner.RsHandlers;
import org.sample.shop.db.queryrunner.Statements;
import org.sample.shop.entity.Item;

import java.util.List;

public enum ItemDAOImpl implements ItemDAO {

    INSTANCE;

    @Override
    public List<Item> listByUidAndStatus(Long uid, int status, int curPage, int pageSize) {
        int start = curPage * pageSize + 1;
        int end = start + pageSize;
        return QueryRunnerProxy.query(Statements.ITEM_LIST_BY_UID_AND_STATUS, RsHandlers.ITEM_LIST, uid, status, start, end);
    }

    @Override
    public int saveItem(Item item) {
        return QueryRunnerProxy.update(Statements.ITEM_SAVE_ITEM, item.getUserId(), item.getName(), item.getPrice(),
                item.getStatus(), item.getQuantity());
    }

    @Override
    public int removeById(Long id) {
        return QueryRunnerProxy.update(Statements.ITEM_REMOVE_BY_ID, id);
    }

    @Override
    public int updateById(Item item) {
        // 如果有其它连接在操作item表（例如管理员登陆Oracle数据库），该调用会阻塞！！！
        return QueryRunnerProxy.update(Statements.ITEM_UPDATE_BY_ID, item.getName(), item.getPrice(), item.getStatus(), item.getQuantity(), item.getId());
    }

    @Override
    public Item getById(Long id) {
        return QueryRunnerProxy.query(Statements.ITEM_GET_BY_ID, RsHandlers.ITEM, id);
    }
}
