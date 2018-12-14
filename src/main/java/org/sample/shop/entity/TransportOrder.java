package org.sample.shop.entity;

public class TransportOrder {

    private Long id;
    private OrderDetail detail;
    private String location;
    private int status;

    public TransportOrder() {
    }

    public TransportOrder(OrderDetail detail, String location, int status) {
        this.detail = detail;
        this.location = location;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDetail getDetail() {
        return detail;
    }

    public void setDetail(OrderDetail detail) {
        this.detail = detail;
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
                ", detail=" + detail +
                ", location='" + location + '\'' +
                ", status=" + status +
                '}';
    }
}
