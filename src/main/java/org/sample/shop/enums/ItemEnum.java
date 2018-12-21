package org.sample.shop.enums;

public enum ItemEnum {

    ON("上架状态", 1),
    OFF("下架状态", 0);

    private String description;

    private int status;

    ItemEnum(String description, int status) {
        this.description = description;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }
}
