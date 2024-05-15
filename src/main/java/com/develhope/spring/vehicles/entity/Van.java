package com.develhope.spring.vehicles.entity;

public class Van implements Vehicles {

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
    String accessories;
    boolean isNew;
    VehicleState vehicleState;


    @Override
    public void addToDealer() {

    }
}
