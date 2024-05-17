package com.develhope.spring.vehicles.entity;

public class Car implements Vehicles {
    private String brand;
    private String model;
    private int engineSize;
    private String color;
    private int power;
    private String gearbox;
    private int registrationYear;
    private FuelType fuelType;
    private double price;
    private double discount;
    private String accessories;
    private boolean isNew;
    private VehicleState vehicleState;


    @Override
    public void addToDealer() {

    }
}