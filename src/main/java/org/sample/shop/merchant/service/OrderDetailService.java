package org.sample.shop.merchant.service;

import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.dataobject.OrderDetail;

import java.util.List;

/**
 * OrderDetail不会自己创建，总是必须先有订单。
 * 所以该对象的创建方法实际上在OrderService里。
 */
public interface OrderDetailService {

    ServiceResult<List<OrderDetail>> getByMerchantId(long merchantId);

    ServiceResult<List<OrderDetail>> getByOrderId(long oid);

    /**
     * 买家支付，改变OrderDetail状态
     * @param detailId OrderDetail detailId
     * @return
     */
    ServiceResult<OrderDetail> pay(long detailId);

    /**
     * @param detailId OrderDetail detailId
     * @return
     */
    ServiceResult<OrderDetail> completed(long detailId);

    ServiceResult<OrderDetail> deliver (long detailId, long expressUid);
}
