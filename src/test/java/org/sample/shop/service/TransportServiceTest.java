package org.sample.shop.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.sample.shop.dto.ServiceResult;
import org.sample.shop.entity.TransportOrder;
import org.sample.shop.service.impl.TransportServiceImpl;

import java.util.List;

public class TransportServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private final TransportService transportService = TransportServiceImpl.INSTANCE;

    @Test
    public void createNew() throws Exception {
        ServiceResult<TransportOrder> result = transportService.createNew(1000L, 1000L, "北京");
        LOGGER.debug(result);
    }

    @Test
    public void getByUid() throws Exception {
        ServiceResult<List<TransportOrder>> result = transportService.getByUid(1000L, 1, 2);
        LOGGER.debug(result);
    }

    @Test
    public void getByDetailId() throws Exception {
        ServiceResult<TransportOrder> result = transportService.getByDetailId(1000L);
        LOGGER.debug(result);
    }

    @Test
    public void updateLocation() throws Exception {
        ServiceResult<TransportOrder> result = transportService.updateLocation(1000L, "天空之城");
        LOGGER.debug(result);
    }

    @Test
    public void finishOrder() throws Exception {
        ServiceResult<TransportOrder> result = transportService.finishOrder(1000L);
        LOGGER.debug(result);
    }
}