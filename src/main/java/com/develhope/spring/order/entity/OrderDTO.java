package com.develhope.spring.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class OrderDTO {

    private long userId;

    private double deposit;

    private boolean payed;

    private long vehicleId;
}
