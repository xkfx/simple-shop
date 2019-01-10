package org.sample.shop.dao;

import org.sample.shop.entity.Item;

import java.util.List;

public interface ItemDAO {

    List<Item> listByUidAndStatus(Long uid, int status, int curPage, int pageSize);

    List<Item> listByUidAndStatusNew(Long uid, int status, int start, int offset);

    int saveItem(Item item);

    int removeById(Long id);

    int updateById(Item item);

    Item getById(Long id);
}
