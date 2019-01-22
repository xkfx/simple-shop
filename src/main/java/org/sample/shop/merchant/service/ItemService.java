package org.sample.shop.merchant.service;

import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.Item;

import java.util.List;

public interface ItemService {

    ServiceResult<Item> createNew(Long uid, String name, double price, int quantity);

    ServiceResult<List<Item>> listByUidAndStatus(Long uid, Integer status, Integer start, Integer offset);

    ServiceResult<List<Item>> listByUidAndStatus(Long uid, Integer status);

    ServiceResult<List<Item>> listByUidAndStatus(Long uid);

    ServiceResult<Item> delete(Long id);

    ServiceResult<Item> updateInfo(Long id, String name, double price, int quantity); // TODO 只改个别属性不方便

    ServiceResult<Item> updateStatus(Long id, int status);
}
