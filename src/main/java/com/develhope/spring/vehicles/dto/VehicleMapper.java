package com.develhope.spring.vehicles.dto;
import com.develhope.spring.vehicles.entity.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleMapper {
    public static VehicleDTO toDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setVehicleKind(vehicle.getVehicleKind());
        dto.setBrand(vehicle.getBrand());
        dto.setModel(vehicle.getModel());
        dto.setEngineSize(vehicle.getEngineSize());
        dto.setColor(vehicle.getColor());
        dto.setPower(vehicle.getPower());
        dto.setGearbox(vehicle.getGearbox());
        dto.setRegistrationYear(vehicle.getRegistrationYear());
        dto.setFuelType(vehicle.getFuelType());
        dto.setPrice(vehicle.getPrice());
        dto.setDiscounted(vehicle.isDiscounted());
        dto.setDiscount(vehicle.getDiscount());
        dto.setAccessories(vehicle.getAccessories());
        dto.setNew(vehicle.isNew());
        dto.setVehicleState(vehicle.getVehicleState());
        dto.setPurchased(vehicle.isPurchased());
        dto.setRented(vehicle.isRented());
        return dto;
    }

    public static List<VehicleDTO> toDTO(List<Vehicle> vehicles) {
        return vehicles.stream()
                .map(VehicleMapper::toDTO)
                .collect(Collectors.toList());
    }
}
