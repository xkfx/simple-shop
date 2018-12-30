package org.sample.shop.enums.entitystatus.impl;

import org.sample.shop.enums.entitystatus.EntityStatus;

public enum OrderDetailStatus implements EntityStatus {

    NOT_PAID(0),
    NOT_DELIVERED(1),
    DELIVERED(2),
    WAIT_PICK(3),
    COMPLETED(4);

    private final int code;

    OrderDetailStatus(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
