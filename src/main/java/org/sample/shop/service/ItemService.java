package org.sample.shop.service;

import org.sample.shop.entity.Item;

import java.util.List;

public interface ItemService {

    ServiceResult<Item> createNew(Long uid, String name, double price, int quantity);

    ServiceResult<List<Item>> listOnSale(Long uid, int curPage); // 客户调用

    ServiceResult<List<Item>> listOnSale(Long uid, int curPage, int pageSize);

    ServiceResult<List<Item>> listOffSale(Long uid, int curPage); // 商家调用

    ServiceResult<List<Item>> listOffSale(Long uid, int curPage, int pageSize);

    ServiceResult<Item> delete(Long id);

    ServiceResult<Item> updateInfo(Long id, String name);

    ServiceResult<Item> updateInfo(Long id, double price);

    ServiceResult<Item> updateInfo(Long id, int quantity);

    ServiceResult<Item> updateInfo(Long id, String name, double price);

    ServiceResult<Item> updateInfo(Long id, String name, int quantity);

    ServiceResult<Item> updateInfo(Long id, double price, int quantity);

    ServiceResult<Item> updateInfo(Long id, String name, double price, int quantity); // TODO 只改个别属性不方便

    ServiceResult<Item> upShelf(Long id);

    ServiceResult<Item> offShelf(Long id);
}