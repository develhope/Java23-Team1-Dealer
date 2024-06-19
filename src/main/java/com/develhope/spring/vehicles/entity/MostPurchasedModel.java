package com.develhope.spring.vehicles.entity;

import lombok.Data;

@Data
public class MostPurchasedModel {
    private String brand;
    private String model;
    private long numberOfPurchases;
}
