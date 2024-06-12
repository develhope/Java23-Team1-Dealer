package com.develhope.spring.vehicles.service;

import com.develhope.spring.exception.customException.NoResultsException;
import com.develhope.spring.exception.customException.VehicleNotFoundException;
import com.develhope.spring.order.repository.OrderRepository;
import com.develhope.spring.vehicles.entity.*;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private OrderRepository orderRepository;

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

    public String findMostOrderedVehicleModel() {
        String model = vehicleRepository.findMostOrderedVehicleModel();
        if (model != null && !orderRepository.findAll().isEmpty()) {
            return model;
        } else throw new NoResultsException("no valid data for this report function");


    }

    public boolean existsById(Long id) {
        return vehicleRepository.existsById(id);
    }


}
