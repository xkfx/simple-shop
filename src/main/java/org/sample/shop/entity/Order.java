package org.sample.shop.entity;

import java.util.List;

public class Order {

    private Long id;
    private double total;
    private List<OrderDetail> details;

    public Order() {
    }

    public Order(double total, List<OrderDetail> details) {
        this.total = total;
        this.details = details;
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
                ", total=" + total +
                ", details=" + details +
                '}';
    }
}
