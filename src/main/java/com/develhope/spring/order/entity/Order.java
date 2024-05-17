package com.develhope.spring.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private long orderId;
    private long buyerId;
    private double deposit;
    boolean payed;
    private OrderStatus orderStatus;

    public Order(long orderId, long buyerId, double deposit, boolean payed, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.deposit = deposit;
        this.payed = payed;
        this.orderStatus = orderStatus;
    }

    public Order() {

    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
