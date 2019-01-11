package org.sample.shop.merchant.manager;

import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.TransportOrder;
import org.sample.shop.express.service.TransportService;
import org.sample.shop.express.service.impl.TransportServiceImpl;

public class TransportManager4merchant {

    private TransportService service = new TransportServiceImpl();

    public ServiceResult<TransportOrder> createNew(long userId, long detailId, String location) {
        return service.createNew(userId, detailId, location);
    }
}
