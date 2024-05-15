package com.develhope.spring.vehicles.entity;

import jakarta.persistence.Entity;

@Entity
public class Scooter extends Vehicles {

    public Scooter(String brand, String model, int engineSize, String color, int power, String gearbox, int registrationYear, FuelType fuelType, double price, double discount, String accessories, boolean isNew, VehicleState vehicleState) {
        super(brand, model, engineSize, color, power, gearbox, registrationYear, fuelType, price, discount, accessories, isNew, vehicleState);
    }
    public Scooter(){}
}
