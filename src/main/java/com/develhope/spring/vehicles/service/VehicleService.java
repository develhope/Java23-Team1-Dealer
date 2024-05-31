package com.develhope.spring.vehicles.service;

import com.develhope.spring.exception.NoResultsException;
import com.develhope.spring.exception.OrderNotFoundException;
import com.develhope.spring.exception.UserNotFoundException;
import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.repository.OrderRepository;
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
    private OrderRepository orderRepository;

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public boolean shopVehicle(Vehicle vehicle, VehicleState isPurchasable){
        Order newOrder = new Order();
        if (vehicle == null || isPurchasable == null) {
            throw new NoResultsException("These fields cannot be null.");
        }
        if (vehicle.getVehicleState() == isPurchasable || vehicle.isAvailable()) {
            orderRepository.save(newOrder);
            System.out.println("Vehicle has been successfully purchased!");
            return true;
        } else {
            throw new OrderNotFoundException("Vehicle purchase failed, please call our agency for more information.");
        }
    }

    public List<Vehicle> findPurchasedVehiclesByUserId(Long userId, Order order) {
        return vehicleRepository.findPurchasesVehicleByUserId(userId, order);
    }

    public List<Vehicle> findByVehicleState(VehicleState vehicleState) {
        List<Vehicle> results = vehicleRepository.findByVehicleState(vehicleState);
        if (results.isEmpty()) throw new NoResultsException("The search has not returned any results");
        return results;
    }
}