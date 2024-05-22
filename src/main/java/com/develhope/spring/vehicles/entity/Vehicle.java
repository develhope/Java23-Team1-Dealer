package com.develhope.spring.vehicles.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table
@Data
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    @NonNull
    private VehicleKind vehicleKind;
    @NonNull
    private String brand;
    @NonNull
    private String model;
    @NonNull
    private int engineSize;
    @NonNull
    private String color;
    @NonNull
    private int power;
    @NonNull
    private String gearbox;
    @NonNull
    private int registrationYear;
    @NonNull
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @NonNull
    private double price;
    @NonNull
    private double discount;
    @NonNull
    private String accessories;
    @NonNull
    private boolean isNew;
    @Enumerated(EnumType.STRING)
    private VehicleState vehicleState;
}