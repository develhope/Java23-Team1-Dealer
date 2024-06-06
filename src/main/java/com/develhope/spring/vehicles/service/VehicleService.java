package com.develhope.spring.vehicles.service;

import com.develhope.spring.exception.NoResultsException;
import com.develhope.spring.exception.VehicleNotFoundException;
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
    public List<Vehicle> findByVehicleState(VehicleState vehicleState){
        List<Vehicle> results = vehicleRepository.findByVehicleState(vehicleState);
        if (results.isEmpty()) throw new NoResultsException("La ricerca non ha prodotto nessun risultato");
        return results;
    }
    public void deleteVehicle(long vehicleId) {
        if (!vehicleRepository.existsById(vehicleId)) {
            throw new VehicleNotFoundException(
                    "vehicle not found"
            );
        }
        vehicleRepository.deleteById(vehicleId);

    }
}