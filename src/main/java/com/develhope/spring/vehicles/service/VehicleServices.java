package com.develhope.spring.vehicles.service;

import com.develhope.spring.user.exception.UserNotFoundException;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServices {
    @Autowired
    private VehicleRepository vehicleRepository;

    public void deleteVehicle(long vehicleId) {
        if (!vehicleRepository.existsById(vehicleId)) {
            throw new IllegalArgumentException(
                    "vehicle not found"
            );
        }
        vehicleRepository.deleteById(vehicleId);

    }

}
