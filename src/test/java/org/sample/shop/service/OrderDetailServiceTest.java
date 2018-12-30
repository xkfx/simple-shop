package org.sample.shop.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.sample.shop.service.impl.OrderDetailServiceImpl;

import static org.junit.Assert.*;

public class OrderDetailServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private final OrderDetailService service = new OrderDetailServiceImpl();

    @Test
    public void getByMerchantId() throws Exception {
        ServiceResult result = service.getByMerchantId(1000L);
        LOGGER.debug(result);
    }

    @Test
    public void getByOrderId() throws Exception {
        ServiceResult result = service.getByOrderId(1000L);
        LOGGER.debug(result);
    }

    @Test
    public void pay() throws Exception {
        ServiceResult result = service.pay(1000L);
        LOGGER.debug(result);
    }

    @Test
    public void completed() throws Exception {
        ServiceResult result = service.completed(1000L);
        LOGGER.debug(result);
    }

}