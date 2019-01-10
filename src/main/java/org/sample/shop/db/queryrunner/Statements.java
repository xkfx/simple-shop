package org.sample.shop.db.queryrunner;

import org.sample.shop.db.queryrunner.statementfactory.StatementFactory;

public interface Statements {
    // item
    String ITEM_LIST_BY_UID_AND_STATUS = StatementFactory.getStatement("#ITEM_LIST_BY_UID_AND_STATUS");
    String ITEM_SAVE_ITEM = StatementFactory.getStatement("#ITEM_SAVE_ITEM");
    String ITEM_REMOVE_BY_ID = StatementFactory.getStatement("#ITEM_REMOVE_BY_ID");
    String ITEM_UPDATE_BY_ID = StatementFactory.getStatement("#ITEM_UPDATE_BY_ID");
    String ITEM_GET_BY_ID = StatementFactory.getStatement("#ITEM_GET_BY_ID");
    // order
    String ORDER_GET_BY_UID = "SELECT id, user_id AS userId, total FROM simple_order WHERE user_id=?";
    String ORDER_SAVE_ORDER = "INSERT INTO simple_order(user_id, total) VALUES(?, ?)";
    String ORDER_SAVE_ORDER_DETAIL = "INSERT INTO order_detail(order_id, item_id, user_id, quantity, price, status) VALUES(?, ?, ?, ?, ?, ?)";
    // order detail
    String ORDER_DETAIL_GET_BY_ORDER_ID = "SELECT id, order_id AS orderId, item_id AS itemId, user_id AS userId, quantity, price, status FROM order_detail WHERE order_id=?";
    String ORDER_DETAIL_GET_BY_UID = "SELECT id, order_id AS orderId, item_id AS itemId, user_id AS userId, quantity, price, status FROM order_detail WHERE user_id=?";
    String ORDER_DETAIL_UPDATE_BY_ID = "UPDATE order_detail SET status=? WHERE id=?";
    // user
    String USER_GET = "SELECT id, username, password, type FROM simple_user WHERE username=? AND password=?";
    String USER_GET_BY_USERNAME = "SELECT id, username, password, type FROM simple_user WHERE username=?";
    String USER_SAVE_USER = "INSERT INTO simple_user(type, username, password) VALUES (?, ?, ?)";
    String USER_UPDATE_BY_ID = "UPDATE simple_user SET password=? WHERE id=?";
    // transport order
    String TRANSPORT_ORDER_SAVE_ORDER = "INSERT INTO transport_order(user_id, order_detail_id, location, status) VALUES (?, ?, ?, ?)";
    String TRANSPORT_ORDER_GET_BY_UID = StatementFactory.getStatement("#TRANSPORT_ORDER_GET_BY_UID");;
    String TRANSPORT_ORDER_GET_BY_DETAIL_ID = "SELECT id, user_id AS userId, order_detail_id AS detailId, location, status FROM transport_order WHERE order_detail_id=?";
    String TRANSPORT_ORDER_GET_BY_ID = "SELECT id, user_id AS userId, order_detail_id AS detailId, location, status FROM transport_order WHERE id=?";
    String TRANSPORT_ORDER_UPDATE_BY_ID = "UPDATE transport_order SET location=?, status=? WHERE id=?";
}
