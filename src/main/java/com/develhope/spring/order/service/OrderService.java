package com.develhope.spring.order.service;

import com.develhope.spring.order.dto.OrderDTO;
import com.develhope.spring.order.entity.OrderStatus;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.repository.OrderRepository;
import com.develhope.spring.exception.OrderNotFoundException;
import com.develhope.spring.user.repository.UserRepository;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;


    public Order createOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Vehicle vehicle = vehicleRepository.findById(orderDTO.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        Order orderEntity = new Order();
        orderEntity.setUser(user);
        orderEntity.setVehicle(vehicle);
        orderEntity.setOrderStatus(OrderStatus.ORDERED);
        orderEntity.setDeposit(orderDTO.getDeposit());
        orderEntity.setPayed(orderDTO.isPayed());
        return orderRepository.save(orderEntity);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    public Order findOrderById(long id) {
        if (orderRepository.findById(id).isPresent()) {
            return orderRepository.findById(id).get();
        } else {
            throw new OrderNotFoundException("No order founded with this id: " + id);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    public Order updateOrder(long id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    public void deleteOrderById(long id) {
        orderRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
