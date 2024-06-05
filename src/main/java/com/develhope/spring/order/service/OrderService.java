package com.develhope.spring.order.service;

import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.repository.OrderRepository;
import com.develhope.spring.exception.OrderNotFoundException;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleState;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    public Order shopVehicle(Vehicle vehicle, VehicleState isPurchasable, User userId) {
        if (vehicle == null || isPurchasable == null) {
            throw new IllegalArgumentException("Vehicle and isPurchasable parameters cannot be null.");
        }
        if (vehicle.getVehicleState() == isPurchasable && vehicle.isAvailable()) {
            Order newOrder = new Order();
            newOrder.setVehicle(vehicle);
            newOrder.setUser(userId);
            orderRepository.save(newOrder);
            return newOrder;
        } else {
            throw new OrderNotFoundException("Vehicle purchase failed, the vehicle is not purchasable.");
        }
    }

    public Order findOrderById(long id) {
        if (orderRepository.existsById(id)) {
            return orderRepository.findById(id).get();
        } else {
            throw new OrderNotFoundException("No order founded with this id: " + id);
        }
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder (long id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }

    public void deleteOrderById(long id) {
        orderRepository.deleteById(id);
    }

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
