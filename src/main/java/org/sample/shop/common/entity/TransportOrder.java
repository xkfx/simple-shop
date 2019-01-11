package org.sample.shop.common.entity;

public class TransportOrder {

    private long id; // 运单id
    private long userId; // 物流id
    private long detailId;
    private String location; // 描述当前位置
    private int status; // 物流状态，大体分为：0等待出货，1运送中，2正在派件，3完成

    public TransportOrder() {
    }

    public TransportOrder(long userId, long detailId, String location, int status) {
        this.userId = userId;
        this.detailId = detailId;
        this.location = location;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransportOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", detailId=" + detailId +
                ", location='" + location + '\'' +
                ", status=" + status +
                '}';
    }
}
