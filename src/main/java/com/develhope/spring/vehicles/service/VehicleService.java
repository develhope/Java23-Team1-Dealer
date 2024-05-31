package com.develhope.spring.vehicles.service;

import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleState;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle insertVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public boolean shopVehicle(Vehicle vehicle, VehicleState isPurchasable) {
        if (vehicle.getVehicleState() == isPurchasable || vehicle.isAvailable()) {
            System.out.println("Vehicle has been successfully purchased!");
            return true;
        } else {
            throw new IllegalArgumentException("Vehicle purchase failed, please call our agency for more information.");
        }
    }
}
