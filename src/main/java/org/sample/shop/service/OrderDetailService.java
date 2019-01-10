package org.sample.shop.service;

import org.sample.shop.dto.ServiceResult;
import org.sample.shop.entity.OrderDetail;

import java.util.List;

/**
 * OrderDetail不会自己创建，总是必须先有订单。
 * 所以该对象的创建方法实际上在OrderService里。
 */
public interface OrderDetailService {

    ServiceResult<List<OrderDetail>> getByMerchantId(long uid);

    ServiceResult<List<OrderDetail>> getByOrderId(long oid);

    /**
     * 买家支付，改变OrderDetail状态
     * @param id OrderDetail id
     * @return
     */
    ServiceResult<OrderDetail> pay(long id);

    /**
     * 买家确认收货，除了两个主动的状态改变外，
     * 其余状态都是需要其它服务触发的！
     * @param id OrderDetail id
     * @return
     */
    ServiceResult<OrderDetail> completed(long id);
}
