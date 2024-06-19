package com.develhope.spring.vehicles.service;

import com.develhope.spring.exception.customException.NoResultsException;
import com.develhope.spring.exception.customException.VehicleNotFoundException;
import com.develhope.spring.vehicles.dto.MostOrderedVehicleDto;
import com.develhope.spring.vehicles.dto.VehicleMapper;
import com.develhope.spring.vehicles.entity.*;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;




@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

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

    public MostOrderedVehicleDto findMostOrderedVehicleModel() {
        MostOrderedVehicleModelCount mostOrderedVehicleModelCount = vehicleRepository.findMostOrderedVehicleModel();

        if (mostOrderedVehicleModelCount == null
                || mostOrderedVehicleModelCount.getModel() == null) {
            throw new NoResultsException("no valid data for this report function");
        }
        return vehicleMapper.mostOrderedVehicleModelCountToDto(mostOrderedVehicleModelCount);

    }

    public boolean existsById(Long id) {
        return vehicleRepository.existsById(id);
    }


}
