package com.develhope.spring.vehicles.service;

import com.develhope.spring.exception.customException.VehicleNotFoundException;
import com.develhope.spring.vehicles.entity.*;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicleById(long vehicleId) {
        if (!vehicleRepository.existsById(vehicleId)) {
            throw new VehicleNotFoundException(
                    "Vehicle with id " + vehicleId + " not found"
            );
        }
        vehicleRepository.deleteById(vehicleId);
    }

    public boolean existsById(Long id) {
        return vehicleRepository.existsById(id);
    }


}
