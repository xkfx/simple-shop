package org.sample.shop.merchant.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.common.dao.ItemDAO;
import org.sample.shop.common.dao.impl.ItemDAOImpl;
import org.sample.shop.common.db.connmanager.LocalConnectionProxy;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.dataobject.ItemDO;
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
    public ServiceResult<ItemDO> createNew(Long uid, String name, double price, int quantity) {
        ItemDO itemDO = new ItemDO(uid, name, price, ItemStatus.OFF_SALE.getCode(), quantity);
        try {
            itemDAO.saveItem(itemDO);
            return new ServiceResult<>(ITEM_CREATE_SUCCESS, itemDO);
        } catch (DaoException e) {
            return new ServiceResult<>(ITEM_CREATE_FAIL);
        } finally {
            LocalConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<List<ItemDO>> listByUidAndStatus(Long uid) {
        int defaultStatus = ItemStatus.ON_SALE.getCode();
        return listByUidAndStatus(uid, defaultStatus);
    }

    @Override
    public ServiceResult<List<ItemDO>> listByUidAndStatus(Long uid, Integer status) {
        int defaultOffset = Short.MAX_VALUE;
        return listByUidAndStatus(uid, status, 1, defaultOffset);
    }

    @Override
    public ServiceResult<List<ItemDO>> listByUidAndStatus(Long uid, Integer status, Integer start, Integer offset) {
        try {
            return new ServiceResult<>(ITEM_LIST_SUCCESS, itemDAO.listByUidAndStatus(uid, status, start, offset));
        } catch (DaoException e) {
            return new ServiceResult<>(ITEM_LIST_FAIL);
        } finally {
            LocalConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<ItemDO> delete(Long id) {
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
    public ServiceResult<ItemDO> updateInfo(Long id, String name, double price, int quantity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("price", price);
        parameters.put("quantity", quantity);
        return abstractUpdate(id, parameters);
    }

    @Override
    public ServiceResult<ItemDO> updateStatus(Long id, int status) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("status", status);
        return abstractUpdate(id, parameters);
    }

    private ServiceResult<ItemDO> abstractUpdate(Long id, Map<String, Object> parameters) {
        try {
            ItemDO itemDO = itemDAO.getById(id);
            if (itemDO != null) {
                final Object name = parameters.get("name");
                final Object price = parameters.get("price");
                final Object status = parameters.get("status");
                final Object quantity = parameters.get("quantity");
                itemDO.setName(name == null ? itemDO.getName() : String.valueOf(name));
                itemDO.setPrice(price == null ? itemDO.getPrice() : (double) price);
                itemDO.setStatus(status == null ? itemDO.getStatus() : (int) status);
                itemDO.setQuantity(quantity == null ? itemDO.getQuantity() : (int) quantity);
                if (itemDAO.updateById(itemDO) == 1) {
                    return new ServiceResult<>(ITEM_UPDATE_SUCCESS, itemDO);
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
