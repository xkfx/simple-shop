package org.sample.shop.entity;

public class OrderDetail {

    private Long id;
    private Long orderId;
    private Long itemId;
    private Long userId; // 卖家id
    private int quantity;
    private double price;
    private int status;

    public OrderDetail() {
    }

    public OrderDetail(Long orderId, Long itemId, Long userId, int quantity, double price, int status) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public OrderDetail(Long id, int status) {
        this.id = id;
        this.status = status;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
                ", orderId=" + orderId +
                ", itemId=" + itemId +
                ", userId=" + userId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
