package org.sample.shop.common.entity;

public class Item {

    private Long id;
    private Long userId;
    private String name;
    private double price;
    private int status;
    private int quantity;

    public Item() {
    }

    public Item(Long userId, String name, double price, int status, int quantity) {
        this.userId = userId;
        this.name = name;
        this.price = price;
        this.status = status;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", quantity=" + quantity +
                '}';
    }
}
