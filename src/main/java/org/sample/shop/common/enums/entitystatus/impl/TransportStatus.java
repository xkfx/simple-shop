package org.sample.shop.common.enums.entitystatus.impl;

import org.sample.shop.common.enums.entitystatus.EntityStatus;

public enum TransportStatus implements EntityStatus {

    WAITING(0),
    TRAVELLING(1),
    DELIVERING(2),
    FINISH(3);

    private final int code;

    TransportStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
