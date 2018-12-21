package org.sample.shop.enums;

public enum ServiceEnum {

    LOGIN_SUCCESS("注册成功", 200),
    REGISTER_SUCCESS("注册成功", 201),
    USER_EXISTED("用户名已被注册", 400),
    LOGIN_FAIL("用户名或者密码错误", 400),
    INNER_ERROR("系统异常！", 500);

    private String description;

    private int status;

    ServiceEnum(String description, int status) {
        this.description = description;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return description;
    }
}
