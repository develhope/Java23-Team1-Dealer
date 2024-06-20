package com.develhope.spring.purchase_order.dto;

import lombok.Data;

@Data
public class OrderCountDTO {
    private String brand;
    private String model;
    private Long orderCount;

    public OrderCountDTO(String brand, String model, Long orderCount) {
        this.brand = brand;
        this.model = model;
        this.orderCount = orderCount;
    }
}
