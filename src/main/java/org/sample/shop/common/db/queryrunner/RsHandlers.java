package org.sample.shop.common.db.queryrunner;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.sample.shop.common.entity.*;

public interface RsHandlers {

    BeanHandler<User> USER = new BeanHandler<>(User.class);

    BeanHandler<Item> ITEM = new BeanHandler<>(Item.class);

    BeanListHandler<Item> ITEM_LIST = new BeanListHandler<>(Item.class);

    BeanListHandler<Order> ORDER_LIST = new BeanListHandler<>(Order.class);

    BeanListHandler<OrderDetail> ORDER_DETAIL_LIST = new BeanListHandler<>(OrderDetail.class);

    BeanHandler<TransportOrder> TRANSPORT_ORDER = new BeanHandler<>(TransportOrder.class);

    BeanListHandler<TransportOrder> TRANSPORT_ORDER_LIST = new BeanListHandler<>(TransportOrder.class);

    BeanListHandler<Cart> cart_list = new BeanListHandler<>(Cart.class);

    MapHandler mapHandler = new MapHandler();
}
