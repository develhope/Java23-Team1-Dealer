package com.develhope.spring.vehicles.dto;

import com.develhope.spring.vehicles.entity.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleMapper {
    public static VehicleDTO toDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setModel(vehicle.getModel());
        dto.setBrand(vehicle.getBrand());
        return dto;
    }

    public static List<VehicleDTO> toDTO(List<Vehicle> vehicles) {
        return vehicles.stream()
                .map(VehicleMapper::toDTO)
                .collect(Collectors.toList());
    }
}
