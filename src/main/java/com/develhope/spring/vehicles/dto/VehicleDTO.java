package com.develhope.spring.vehicles.dto;

import com.develhope.spring.vehicles.entity.FuelType;
import com.develhope.spring.vehicles.entity.Gearbox;
import com.develhope.spring.vehicles.entity.VehicleKind;
import com.develhope.spring.vehicles.entity.VehicleState;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    @Enumerated(EnumType.STRING)
    private VehicleKind vehicleKind;
    private String brand;
    private String model;
    private int engineSize;
    private String color;
    private int power;
    @Enumerated(EnumType.STRING)
    private Gearbox gearbox;
    private int registrationYear;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    private double price;
    private boolean isDiscounted;
    private double discount;
    private String accessories;
    private boolean isNew;
    @Enumerated(EnumType.STRING)
    private VehicleState vehicleState;
    private boolean purchased;
    private boolean rented;
}