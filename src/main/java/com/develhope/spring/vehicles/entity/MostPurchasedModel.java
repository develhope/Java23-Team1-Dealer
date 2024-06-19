package com.develhope.spring.vehicles.entity;

import lombok.Data;

public interface MostPurchasedModel {
    String getBrand();
    String getModel();
    Long getNumberOfPurchases();
}
