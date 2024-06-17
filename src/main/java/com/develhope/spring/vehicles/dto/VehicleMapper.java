package com.develhope.spring.vehicles.dto;

import com.develhope.spring.vehicles.entity.Vehicle;

import org.springframework.stereotype.Component;


@Component

public class VehicleMapper {


    public MostOrderedVehicleDto vehicleToDto(Vehicle vehicle) {
        return new MostOrderedVehicleDto(vehicle.getBrand(), vehicle.getModel());


    }

}
