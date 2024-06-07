package com.develhope.spring.vehicles.service;

import com.develhope.spring.exception.NoResultsException;
import com.develhope.spring.exception.VehicleNotFoundException;
import com.develhope.spring.vehicles.entity.*;
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
        List<Vehicle> vehicles = vehicleRepository.findByEngineSizeLessThanEqual(engineSize);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByEngineSizeGreaterThanEqual (int engineSize) {
        List<Vehicle> vehicles = vehicleRepository.findByEngineSizeGreaterThanEqual(engineSize);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    }

    public List<Vehicle> findAll(){
        return vehicleRepository.findAll();
    }
    public List<Vehicle> findByColorContaining (String color) {
        List<Vehicle> vehicles = vehicleRepository.findByColorContaining(color);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByPowerLessThanEqual (int power) {
        List<Vehicle> vehicles = vehicleRepository.findByPowerLessThanEqual(power);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByPowerGreaterThanEqual (int power) {
        List<Vehicle> vehicles = vehicleRepository.findByPowerGreaterThanEqual(power);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByGearbox (Gearbox gearbox) {
        List<Vehicle> vehicles = vehicleRepository.findByGearbox(gearbox);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByRegistrationYearLessThanEqual (int registrationYear) {
        List<Vehicle> vehicles = vehicleRepository.findByRegistrationYearLessThanEqual(registrationYear);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByRegistrationYearGreaterThanEqual (int registrationYear) {
        List<Vehicle> vehicles = vehicleRepository.findByRegistrationYearGreaterThanEqual(registrationYear);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByFuelType (FuelType fuelType) {
        List<Vehicle> vehicles = vehicleRepository.findByFuelType(fuelType);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByPriceLessThanEqual (int price) {
        List<Vehicle> vehicles = vehicleRepository.findByPriceLessThanEqual(price);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByPriceGreaterThanEqual (int price) {
        List<Vehicle> vehicles = vehicleRepository.findByPriceGreaterThanEqual(price);
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByIsDiscountedTrue () {
        List<Vehicle> vehicles = vehicleRepository.findByIsDiscountedTrue();
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByIsNewTrue () {
        List<Vehicle> vehicles = vehicleRepository.findByIsNewTrue();
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

    public List<Vehicle> findByIsNewFalse () {
        List<Vehicle> vehicles = vehicleRepository.findByIsNewFalse();
        if (vehicles.isEmpty()) {
            throw new NoResultsException("No results");
        } else {
            return vehicles;
        }
    }

}
