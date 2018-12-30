package org.sample.shop.enums.business;

/**
 * Enum相比interface常量有更好的可扩展性，所以这边没用interface常量。
 * Service状态码拟定：
 * 100~199通用业务
 * 200~299用户相关
 * 300~399商品相关
 * Service状态码 mod 100 >= 50表示失败
 * （待定义）
 */
public enum BusinessCode {

    INNER_ERROR(150, "系统异常！"),
    LOGIN_SUCCESS(200, "登陆成功"),
    REGISTER_SUCCESS(201, "注册成功"),
    USER_EXISTED(202, "用户名已被注册"),
    LOGIN_FAIL(203, "用户名或者密码错误"),
    ITEM_CREATE_SUCCESS(300, "创建商品成功！"),
    ITEM_LIST_SUCCESS(301, "获取商品列表成功！"),
    ITEM_REMOVE_SUCCESS(302, "删除商品成功！"),
    ITEM_UPDATE_SUCCESS(303, "更新商品成功！"),
    ITEM_CREATE_FAIL(350, "创建商品失败"),
    ITEM_LIST_FAIL(351, "获取商品列表失败"),
    ITEM_EMPTY(352, "商品不存在！"),
    ITEM_UPDATE_FAIL(353, "商品更新失败"),
    TRANSPORT_CREATE_SUCCESS(400, "创建运单成功！"),
    TRANSPORT_GET_SUCCESS(401, "获取运单成功！"),
    TRANSPORT_UPDATE_SUCCESS(402, "运单更新成功！"),
    TRANSPORT_CREATE_FAIL(450, "创建运单失败"),
    TRANSPORT_GET_FAIL(451, "获取运单失败"),
    TRANSPORT_EMPTY(452, "运单不存在！"),
    TRANSPORT_UPDATE_FAIL(453, "运单更新失败"),
    DETAIL_LIST_SUCCESS(500, "获取订单项成功！"),
    DETAIL_UPDATE_SUCCESS(501, "更新订单项成功！"),
    DETAIL_LIST_FAIL(550, "获取订单项失败！"),
    DETAIL_UPDATE_FAIL(551, "更新订单项成功！"),
    ORDER_LIST_SUCCESS(600, "获取订单列表成功！"),
    ORDER_PLACE_SUCCESS(601, "下单成功！"),
    CART_ADD_SUCCESS(602, "添加订单项成功！"),
    CART_RM_SUCCESS(603, "移除订单项成功！"),
    CART_VIEW_SUCCESS(604, "查看购物车成功！"),
    ORDER_LIST_FAIL(654, "获取订单列表失败。"),
    ORDER_PLACE_FAIL(651, "下单失败。"),
    CART_ADD_FAIL(652, "添加订单项失败。"),
    CART_RM_FAIL(653, "移除订单项失败。"),
    CART_VIEW_FAIL(653, "查看购物车失败。"),
//    ITEM_3(),
//    ITEM_4(),
//    ITEM_5(),
    ITEM_6(123, "dsad");

    private final int code;

    private final String defaultDescription;

    BusinessCode(int code, String defaultDescription) {
        this.code = code;
        this.defaultDescription = defaultDescription;
    }

    public String getDefaultDescription() {
        return defaultDescription;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return defaultDescription;
    }
}
