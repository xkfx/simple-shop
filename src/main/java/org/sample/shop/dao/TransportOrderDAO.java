package org.sample.shop.dao;

import org.sample.shop.entity.TransportOrder;

import java.util.List;

public interface TransportOrderDAO {

    int saveOrder(TransportOrder order);

    List<TransportOrder> getByUid(long uid); // 物流调用

    TransportOrder getByDetailId(long id); // 买家&卖家调用

    int updateById(TransportOrder order); // 主要用于更新物流状态
}
