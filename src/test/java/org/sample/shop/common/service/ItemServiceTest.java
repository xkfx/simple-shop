package org.sample.shop.common.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.Item;
import org.sample.shop.merchant.service.ItemService;
import org.sample.shop.merchant.service.impl.ItemServiceImpl;

public class ItemServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ItemService itemService = new ItemServiceImpl();

    @Test
    public void createNew() throws Exception {
        ServiceResult<Item> result = itemService.createNew(80L, "脑白金优惠套装", 123.0, 30);
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
}