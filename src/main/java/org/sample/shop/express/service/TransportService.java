package org.sample.shop.express.service;

import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.dataobject.TransportOrder;

import java.util.List;

public interface TransportService {

    ServiceResult<TransportOrder> createNew(long userId, long detailId, String location);

    ServiceResult<List<TransportOrder>> getByUid(long uid, int start, int offset);

    ServiceResult<TransportOrder> getByDetailId(long detailId);

    ServiceResult<TransportOrder> updateLocation(long id, String location);

    ServiceResult<TransportOrder> updateToWait(long id);

    ServiceResult<TransportOrder> updateToTravel(long id);

    ServiceResult<TransportOrder> updateToDeliver(long id);

    ServiceResult<TransportOrder> finishOrder(long id);
}
