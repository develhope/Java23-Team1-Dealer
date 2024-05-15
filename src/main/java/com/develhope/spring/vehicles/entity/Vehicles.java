package com.develhope.spring.vehicles.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.lang.NonNull;

@Entity
public abstract class Vehicles {
    @Id
    @GeneratedValue
    long id;
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

    public Vehicles(String brand, String model, int engineSize, String color, int power, String gearbox, int registrationYear, FuelType fuelType, double price, double discount, String accessories, boolean isNew, VehicleState vehicleState) {
        this.brand = brand;
        this.model = model;
        this.engineSize = engineSize;
        this.color = color;
        this.power = power;
        this.gearbox = gearbox;
        this.registrationYear = registrationYear;
        this.fuelType = fuelType;
        this.price = price;
        this.discount = discount;
        this.accessories = accessories;
        this.isNew = isNew;
        this.vehicleState = vehicleState;
    }
    public Vehicles(){}

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public String getColor() {
        return color;
    }

    public int getPower() {
        return power;
    }

    public String getGearbox() {
        return gearbox;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public String getAccessories() {
        return accessories;
    }

    public boolean isNew() {
        return isNew;
    }

    public VehicleState getVehicleState() {
        return vehicleState;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public void setVehicleState(VehicleState vehicleState) {
        this.vehicleState = vehicleState;
    }
}
