package org.sample.shop.common.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.customer.service.OrderService;
import org.sample.shop.customer.service.impl.OrderServiceImpl;

public class OrderServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private final OrderService service = new OrderServiceImpl();

    @Test
    public void listOrders() throws Exception {
        ServiceResult result = service.listOrders(1000L);
        LOGGER.debug(result);
    }
}