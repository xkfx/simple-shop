package org.sample.shop.enums.entitystatus.impl;

import org.sample.shop.enums.entitystatus.EntityStatus;

public enum ItemStatus implements EntityStatus {

    ON_SALE(1),
    OFF_SALE(0);

    private final int code;

    ItemStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
