package com.develhope.spring.vehicles.service;

import com.develhope.spring.exception.NoResultsException;
import com.develhope.spring.exception.VehicleNotFoundException;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleKind;
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
        if (results.isEmpty()) throw new NoResultsException("No results");;
        return results;
    }
    public void deleteVehicleById(long vehicleId) {
        if (!vehicleRepository.existsById(vehicleId)) {
            throw new VehicleNotFoundException(
                    "Vehicle with id " + vehicleId + " not found"
            );
        }
        vehicleRepository.deleteById(vehicleId);
    }

    public List<Vehicle> findByVehicleKind (VehicleKind vehicleKind) {
        List<Vehicle> vehicles = vehicleRepository.findByVehicleKind(vehicleKind);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByBrandContaining (String brand) {
        List<Vehicle> vehicles = vehicleRepository.findByBrandContaining(brand);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByModelContaining (String model) {
        List<Vehicle> vehicles = vehicleRepository.findByModelContaining(model);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByEngineSizeLessThanEqual (int engineSize) {
        List<Vehicle> vehicles = vehicleRepository.findByModelContaining(model);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

}
