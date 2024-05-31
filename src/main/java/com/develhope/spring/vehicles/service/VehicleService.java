package com.develhope.spring.vehicles.service;

import com.develhope.spring.exception.NoResultsException;
import com.develhope.spring.exception.UserNotFoundException;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleState;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleState;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle createVehicle(Vehicle vehicle) {
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

    public List<Vehicle> readPurchasedVehiclesByUserId(Long userId) {
        return vehicleRepository.findByUserId(userId);

    public List<Vehicle> findByVehicleState(VehicleState vehicleState){
        List<Vehicle> results = vehicleRepository.findByVehicleState(vehicleState);
        if (results.isEmpty()) throw new NoResultsException("The search has not returned any results");
        return results;

    }
}
