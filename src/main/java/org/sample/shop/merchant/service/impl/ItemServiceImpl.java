package org.sample.shop.merchant.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.common.dao.ItemDAO;
import org.sample.shop.common.dao.impl.ItemDAOImpl;
import org.sample.shop.common.db.connmanager.LocalConnectionProxy;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.Item;
import org.sample.shop.common.enums.entitystatus.impl.ItemStatus;
import org.sample.shop.common.exception.DaoException;
import org.sample.shop.merchant.service.ItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.sample.shop.common.enums.business.BusinessCode.*;

public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public ServiceResult<Item> createNew(Long uid, String name, double price, int quantity) {
        Item item = new Item(uid, name, price, ItemStatus.OFF_SALE.getCode(), quantity);
        try {
            itemDAO.saveItem(item);
            return new ServiceResult<>(ITEM_CREATE_SUCCESS, item);
        } catch (DaoException e) {
            return new ServiceResult<>(ITEM_CREATE_FAIL);
        } finally {
            LocalConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<List<Item>> listByUidAndStatus(Long uid) {
        int defaultStatus = ItemStatus.ON_SALE.getCode();
        return listByUidAndStatus(uid, defaultStatus);
    }

    @Override
    public ServiceResult<List<Item>> listByUidAndStatus(Long uid, Integer status) {
        int defaultOffset = Short.MAX_VALUE;
        return listByUidAndStatus(uid, status, 1, defaultOffset);
    }

    @Override
    public ServiceResult<List<Item>> listByUidAndStatus(Long uid, Integer status, Integer start, Integer offset) {
        try {
            return new ServiceResult<>(ITEM_LIST_SUCCESS, itemDAO.listByUidAndStatus(uid, status, start, offset));
        } catch (DaoException e) {
            return new ServiceResult<>(ITEM_LIST_FAIL);
        } finally {
            LocalConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<Item> delete(Long id) {
        try {
            itemDAO.removeById(id);
            return new ServiceResult<>(ITEM_REMOVE_SUCCESS);
        } catch (DaoException e) {
            return new ServiceResult<>(INNER_ERROR);
        } finally {
            LocalConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<Item> updateInfo(Long id, String name, double price, int quantity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("price", price);
        parameters.put("quantity", quantity);
        return abstractUpdate(id, parameters);
    }

    @Override
    public ServiceResult<Item> updateStatus(Long id, int status) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("status", status);
        return abstractUpdate(id, parameters);
    }

    private ServiceResult<Item> abstractUpdate(Long id, Map<String, Object> parameters) {
        try {
            Item item = itemDAO.getById(id);
            if (item != null) {
                final Object name = parameters.get("name");
                final Object price = parameters.get("price");
                final Object status = parameters.get("status");
                final Object quantity = parameters.get("quantity");
                item.setName(name == null ? item.getName() : String.valueOf(name));
                item.setPrice(price == null ? item.getPrice() : (double) price);
                item.setStatus(status == null ? item.getStatus() : (int) status);
                item.setQuantity(quantity == null ? item.getQuantity() : (int) quantity);
                if (itemDAO.updateById(item) == 1) {
                    return new ServiceResult<>(ITEM_UPDATE_SUCCESS, item);
                } else {
                    return new ServiceResult<>(ITEM_UPDATE_FAIL);
                }
            } else {
                return new ServiceResult<>(ITEM_EMPTY);
            }
        } catch (DaoException e) {
            return new ServiceResult<>(INNER_ERROR);
        } finally {
            LocalConnectionProxy.close();
        }
    }
}
