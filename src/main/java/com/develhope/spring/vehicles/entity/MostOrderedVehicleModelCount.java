package com.develhope.spring.vehicles.entity;

public class MostOrderedVehicleModelCount {
    private String brand;
    private String model;

    public MostOrderedVehicleModelCount(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
