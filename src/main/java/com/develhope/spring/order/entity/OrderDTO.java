package com.develhope.spring.order.entity;

import lombok.Data;

@Data
public class OrderDTO {

    private long userId;

    private double deposit;

    private boolean payed;

    private long vehicleId;
}
