package org.sample.shop.entity;

public class OrderDetail {

    private Long id;
    private Item item;
    private int status;
    private int quantity;
    private double price;

    public OrderDetail() {
    }

    public OrderDetail(Long id, Item item, int status, int quantity, double price) {
        this.id = id;
        this.item = item;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", item=" + item +
                ", status=" + status +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
