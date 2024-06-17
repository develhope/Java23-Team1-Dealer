package com.develhope.spring.vehicles.dto;

import com.develhope.spring.vehicles.entity.MostOrderedVehicleModelCount;
import com.develhope.spring.vehicles.entity.Vehicle;

import org.springframework.stereotype.Component;


@Component

public class VehicleMapper {


    public MostOrderedVehicleDto mostOrderedVehicleModelCountToDto(MostOrderedVehicleModelCount mostOrderedVehicleModelCount) {
        return new MostOrderedVehicleDto(mostOrderedVehicleModelCount.getBrand(), mostOrderedVehicleModelCount.getModel());


    }

}

