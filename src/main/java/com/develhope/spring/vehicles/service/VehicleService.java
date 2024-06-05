package com.develhope.spring.vehicles.service;

import com.develhope.spring.exception.NoResultsException;
import com.develhope.spring.exception.VehicleNotFoundException;
import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.repository.OrderRepository;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleState;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private OrderRepository orderRepository;

    public Vehicle createVehicle(Vehicle vehicle) {
        if (vehicle.getBrand() == null || vehicle.getModel() == null || vehicle.getColor() == null || vehicle.getGearbox() == null || vehicle.getAccessories() == null || vehicle.getEngineSize() < 0 || vehicle.getPower() < 0 || vehicle.getRegistrationYear() < 0 || vehicle.getPrice() < 0) {
            throw new IllegalArgumentException("Invalid vehicle data.");
        }
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> findPurchasedVehiclesByUserId(Long userId) {
        List<Order> orders = orderRepository.findPurchasedVehicleByUserId(userId);
        List<Vehicle> vehicles = new ArrayList<>();
        for (Order order : orders) {
            vehicles.add(order.getVehicle());
        }
        if (orders.isEmpty() || vehicles.isEmpty()) {
            throw new NoResultsException("No orders found for user ID: " + userId);
        }
        return vehicles;
    }

    public List<Vehicle> findByVehicleState(VehicleState vehicleState) {
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