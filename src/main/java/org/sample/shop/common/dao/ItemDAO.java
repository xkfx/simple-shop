package org.sample.shop.common.dao;

import org.sample.shop.common.entity.Item;

import java.util.List;

public interface ItemDAO {

    List<Item> listByUidAndStatus(Long uid, int status, int start, int offset);

    void saveItem(Item item);

    int removeById(Long id);

    int updateById(Item item);

    Item getById(Long id);
}
