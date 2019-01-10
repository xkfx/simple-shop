package org.sample.shop.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.shop.dao.ItemDAO;
import org.sample.shop.dao.impl.ItemDAOImpl;
import org.sample.shop.db.connmanager.ConnectionProxy;
import org.sample.shop.entity.Item;
import org.sample.shop.enums.entitystatus.impl.ItemStatus;
import org.sample.shop.exception.DaoException;
import org.sample.shop.service.ItemService;
import org.sample.shop.dto.ServiceResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.sample.shop.enums.business.BusinessCode.*;

public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ItemDAO itemDAO = ItemDAOImpl.INSTANCE;

    private static final int DEFAULT_PAGE_SIZE = 20;

    @Override
    public ServiceResult<Item> createNew(Long uid, String name, double price, int quantity) {
        Item item = new Item(uid, name, price, ItemStatus.OFF_SALE.getCode(), quantity);
        try {
            itemDAO.saveItem(item);
            return new ServiceResult<>(ITEM_CREATE_SUCCESS, item);
        } catch (DaoException e) {
            return new ServiceResult<>(ITEM_CREATE_FAIL);
        } finally {
            ConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<List<Item>> listOnSale(Long uid, int curPage) {
        return listByUidAndStatus(uid, ItemStatus.ON_SALE, curPage);
    }

    @Override
    public ServiceResult<List<Item>> listOnSale(Long uid, int curPage, int pageSize) {
        return listByUidAndStatus(uid, ItemStatus.ON_SALE, curPage, pageSize);
    }

    @Override
    public ServiceResult<List<Item>> listOffSale(Long uid, int curPage) {
        return listByUidAndStatus(uid, ItemStatus.OFF_SALE, curPage);
    }

    @Override
    public ServiceResult<List<Item>> listOffSale(Long uid, int curPage, int pageSize) {
        return listByUidAndStatus(uid, ItemStatus.OFF_SALE, curPage, pageSize);
    }

    @Override
    public ServiceResult<List<Item>> listByUidAndStatusNew(Long uid) {
        int defaultStatus = ItemStatus.ON_SALE.getCode();
        return listByUidAndStatusNew(uid, defaultStatus);
    }

    @Override
    public ServiceResult<List<Item>> listByUidAndStatusNew(Long uid, Integer status) {
        int defaultOffset = Short.MAX_VALUE;
        return listByUidAndStatusNew(uid, status, 1, defaultOffset);
    }

    @Override
    public ServiceResult<List<Item>> listByUidAndStatusNew(Long uid, Integer status, Integer start, Integer offset) {
        try {
            return new ServiceResult<>(ITEM_LIST_SUCCESS, itemDAO.listByUidAndStatusNew(uid, status, start, offset));
        } catch (DaoException e) {
            return new ServiceResult<>(ITEM_LIST_FAIL);
        } finally {
            ConnectionProxy.close();
        }
    }

    private ServiceResult<List<Item>> listByUidAndStatus(Long uid, ItemStatus status, int curPage) {
        return listByUidAndStatus(uid, status, curPage, DEFAULT_PAGE_SIZE);
    }

    private ServiceResult<List<Item>> listByUidAndStatus(Long uid, ItemStatus status, int curPage, int pageSize) {
        try {
            return new ServiceResult<>(ITEM_LIST_SUCCESS, itemDAO.listByUidAndStatus(uid, status.getCode(), curPage, pageSize));
        } catch (DaoException e) {
            return new ServiceResult<>(ITEM_LIST_FAIL);
        } finally {
            ConnectionProxy.close();
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
            ConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<Item> updateInfo(Long id, String name) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        return abstractUpdate(id, parameters);
    }

    @Override
    public ServiceResult<Item> updateInfo(Long id, double price) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("price", price);
        return abstractUpdate(id, parameters);
    }

    @Override
    public ServiceResult<Item> updateInfo(Long id, int quantity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("quantity", quantity);
        return abstractUpdate(id, parameters);
    }

    @Override
    public ServiceResult<Item> updateInfo(Long id, String name, double price) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("price", price);
        return abstractUpdate(id, parameters);
    }

    @Override
    public ServiceResult<Item> updateInfo(Long id, String name, int quantity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("quantity", quantity);
        return abstractUpdate(id, parameters);
    }

    @Override
    public ServiceResult<Item> updateInfo(Long id, double price, int quantity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("price", price);
        parameters.put("quantity", quantity);
        return abstractUpdate(id, parameters);
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
    public ServiceResult<Item> upShelf(Long id) {
        return abstractUpdateStatus(id, ItemStatus.OFF_SALE);
    }

    @Override
    public ServiceResult<Item> offShelf(Long id) {
        return abstractUpdateStatus(id, ItemStatus.OFF_SALE);
    }

    @Override
    public ServiceResult<Item> updateStatus(Long id, int status) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("status", status);
        return abstractUpdate(id, parameters);
    }

    private ServiceResult<Item> abstractUpdateStatus(Long id, ItemStatus status) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("status", status.getCode());
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
            ConnectionProxy.close();
        }
    }
}
