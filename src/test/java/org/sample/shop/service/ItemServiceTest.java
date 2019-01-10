package org.sample.shop.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.sample.shop.dto.ServiceResult;
import org.sample.shop.entity.Item;
import org.sample.shop.service.impl.ItemServiceImpl;

import java.util.List;

public class ItemServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ItemService itemService = new ItemServiceImpl();

    @Test
    public void createNew() throws Exception {
        ServiceResult<Item> result = itemService.createNew(80L, "脑白金优惠套装", 123.0, 30);
        LOGGER.debug(result);
    }

    @Test
    public void listByUidAndStatus() throws Exception {
        ServiceResult<List<Item>> result = itemService.listOffSale(1000L, 0, 2);
        LOGGER.debug(result);
    }

    @Test
    public void delete() throws Exception {
        ServiceResult<Item> result = itemService.delete(1050L);
        LOGGER.debug(result);
    }

    @Test
    public void updateInfo() throws Exception {
        ServiceResult<Item> result = itemService.updateInfo(1000L, "超群瓦罐3", 33.5, 56);
        LOGGER.debug(result);
    }

    @Test
    public void upShelf() throws Exception {
        ServiceResult<Item> result = itemService.upShelf(1000L);
        LOGGER.debug(result);
    }

    @Test
    public void offShelf() throws Exception {
        ServiceResult<Item> result = itemService.offShelf(1000L);
        LOGGER.debug(result);
    }

}