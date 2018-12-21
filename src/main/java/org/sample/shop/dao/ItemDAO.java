package org.sample.shop.dao;

import org.sample.shop.entity.Item;

import java.util.List;

public interface ItemDAO {

    List<Item> listByUidAndStatus(Long uid, int status, int pageSize, int curPage);

    int saveItem(Item item);

    int removeById(Long id);

    int updateById(Item item);
}
