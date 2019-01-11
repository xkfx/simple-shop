package org.sample.shop.merchant.service;

import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.Item;

import java.util.List;

public interface ItemService {

    ServiceResult<Item> createNew(Long uid, String name, double price, int quantity);

    /**
     * 如果采用RPC风格URL，这样定义Service层方法是可行的，
     * 然而，若是采用RESTful风格URL，这么写就是给自己添堵。。。。。
     * @param uid 用户id
     * @param curPage 当前页码
     */
    @Deprecated
    ServiceResult<List<Item>> listOnSale(Long uid, int curPage); // 客户调用

    @Deprecated
    ServiceResult<List<Item>> listOnSale(Long uid, int curPage, int pageSize);

    @Deprecated
    ServiceResult<List<Item>> listOffSale(Long uid, int curPage); // 商家调用

    @Deprecated
    ServiceResult<List<Item>> listOffSale(Long uid, int curPage, int pageSize);

    ServiceResult<List<Item>> listByUidAndStatusNew(Long uid, Integer status, Integer start, Integer offset);

    ServiceResult<List<Item>> listByUidAndStatusNew(Long uid, Integer status);

    ServiceResult<List<Item>> listByUidAndStatusNew(Long uid);

    ServiceResult<Item> delete(Long id);

    ServiceResult<Item> updateInfo(Long id, String name);

    ServiceResult<Item> updateInfo(Long id, double price);

    ServiceResult<Item> updateInfo(Long id, int quantity);

    ServiceResult<Item> updateInfo(Long id, String name, double price);

    ServiceResult<Item> updateInfo(Long id, String name, int quantity);

    ServiceResult<Item> updateInfo(Long id, double price, int quantity);

    ServiceResult<Item> updateInfo(Long id, String name, double price, int quantity); // TODO 只改个别属性不方便

    @Deprecated
    ServiceResult<Item> upShelf(Long id);

    @Deprecated
    ServiceResult<Item> offShelf(Long id);

    ServiceResult<Item> updateStatus(Long id, int status);
}
