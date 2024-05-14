package com.develhope.spring.vehicles.entity;

public class Car implements Vehicles {
    String brand;
    String model;
    int engineSize;
    String color;
    int power;
    String gearbox;
    int registrationYear;
    FuelType fuelType;
    double price;
    double discount;
    String accesories;
    boolean isNew;
    VehicleState vehicleState;


    @Override
    public void addToDealer() {

    }

}
