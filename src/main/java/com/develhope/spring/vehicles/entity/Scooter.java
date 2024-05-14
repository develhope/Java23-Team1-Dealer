package com.develhope.spring.vehicles.entity;

public class Scooter implements Vehicles {
    String brand;
    String model;
    int engineSize;
    String color;
    int power;
    String gearbox;
    int registrationYear;
    //Fueltype fuelType; bisogna creare classe Fueltype
    double price;
    double discount;
    String accesories;
    boolean isNew;
    //VehicleState vehicleState; bisogna creare classe VehicleState

    @Override
    public void addToDealer() {

    }
}
