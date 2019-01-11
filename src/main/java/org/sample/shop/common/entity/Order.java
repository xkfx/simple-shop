package org.sample.shop.common.entity;

import java.util.List;

public class Order {

    private Long id;
    private Long userId; // 买家id
    private double total;
    private List<OrderDetail> details;

    public Order() {
    }

    public Order(Long userId, double total) {
        this.userId = userId;
        this.total = total;
    }

    public Order(Long userId, double total, List<OrderDetail> details) {
        this.userId = userId;
        this.total = total;
        this.details = details;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", total=" + total +
                ", details=" + details +
                '}';
    }
}
