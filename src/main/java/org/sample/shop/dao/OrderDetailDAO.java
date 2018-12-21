package org.sample.shop.dao;

import org.sample.shop.entity.OrderDetail;

import java.util.List;

public interface OrderDetailDAO {

    /**
     * 买家调用
     * @return
     */
     List<OrderDetail> getByOrderId(long orderId);

    /**
     * 商家调用
     */
    List<OrderDetail> getByUid(long uid);

    /**
     * 商家调用，更改订单状态
     * @param detail
     * @return
     */
    int updateById(OrderDetail detail);
}
