package org.sample.shop.common.dao;

import org.sample.shop.common.dataobject.ItemDO;

import java.util.List;

public interface ItemDAO {

    List<ItemDO> listByUidAndStatus(Long uid, int status, int start, int offset);

    void saveItem(ItemDO itemDO);

    int removeById(Long id);

    int updateById(ItemDO itemDO);

    ItemDO getById(Long id);
}
