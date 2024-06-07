package com.develhope.spring.order.service;

import com.develhope.spring.exception.UserNotFoundException;
import com.develhope.spring.exception.VehicleNotFoundException;
import com.develhope.spring.order.dto.OrderDTO;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.entity.OrderStatus;
import com.develhope.spring.order.repository.OrderRepository;
import com.develhope.spring.exception.OrderNotFoundException;
import com.develhope.spring.user.repository.UserRepository;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        Order orderEntity = new Order();
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Vehicle vehicle = vehicleRepository.findById(orderDTO.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
        orderEntity.setUser(user);
        orderEntity.setVehicle(vehicle);
        orderEntity.setOrderStatus(orderDTO.getOrderStatus());
        orderEntity.setDeposit(orderDTO.getDeposit());
        orderEntity.setPayed(orderDTO.isPayed());
        return orderRepository.save(orderEntity);
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

    public Order updateOrder(long id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }

    public void deleteOrderById(long id) {
        orderRepository.deleteById(id);
    }

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

    public void updateOrderStatus(long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId).get();
        order.setOrderStatus(orderStatus);
    }
}

