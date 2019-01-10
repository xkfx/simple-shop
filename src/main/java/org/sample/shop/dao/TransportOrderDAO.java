package org.sample.shop.dao;

import org.sample.shop.entity.TransportOrder;

import java.util.List;

public interface TransportOrderDAO {

    int saveOrder(TransportOrder order);

    /**
     * 获得指定物流的运单
     * @param uid 物流id
     * @param start 由1开始的起始值
     * @param offset 偏移量，也就是查多少条出来
     * @return
     */
    List<TransportOrder> getByUid(long uid, int start, int offset); // 物流调用

    TransportOrder getByDetailId(long detailId); // 买家&卖家调用

    TransportOrder getById(long id); // 物流调用

    int updateById(TransportOrder order); // 主要用于更新物流状态
}
