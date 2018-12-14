package org.sample.shop.enums;

public enum ServiceEnum {

    REGISTERED_SUCCESSFULLY("注册成功", 201),
    USER_EXISTED("用户名已被注册", 409),
    LOGIN_FAIL("用户名或者密码错误", 1),
    ILLEGAL_USERNAME("无效用户名", 2),
    NULL_USERNAME("用户名不能为空", 3),
    ILLEGAL_PASSWORD("无效密码", 4),
    NULL_PASSWORD("密码不能为空", 5),
    NULL_NICKNAME("昵称不能为空", 6),
    INNER_ERROR("系统异常！", 500);

    private String description;

    private int status;

    ServiceEnum(String description, int status) {
        this.description = description;
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
