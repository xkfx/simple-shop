package org.sample.shop.merchant.service;

import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.dataobject.ItemDO;

import java.util.List;

public interface ItemService {

    ServiceResult<ItemDO> createNew(Long uid, String name, double price, int quantity);

    ServiceResult<List<ItemDO>> listByUidAndStatus(Long uid, Integer status, Integer start, Integer offset);

    ServiceResult<List<ItemDO>> listByUidAndStatus(Long uid, Integer status);

    ServiceResult<List<ItemDO>> listByUidAndStatus(Long uid);

    ServiceResult<ItemDO> delete(Long id);

    ServiceResult<ItemDO> updateInfo(Long id, String name, double price, int quantity); // TODO 只改个别属性不方便

    ServiceResult<ItemDO> updateStatus(Long id, int status);
}
