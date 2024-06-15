package com.develhope.spring.order.service;

import com.develhope.spring.exception.customException.UserNotFoundException;
import com.develhope.spring.exception.customException.VehicleNotFoundException;
import com.develhope.spring.order.dto.OrderDTO;
import com.develhope.spring.order.dto.OrderMapper;
import com.develhope.spring.order.dto.OrderResponseDto;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.entity.OrderStatus;
import com.develhope.spring.order.repository.OrderRepository;
import com.develhope.spring.user.repository.UserRepository;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import com.develhope.spring.exception.customException.OrderNotFoundException;
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
    @Autowired
    private OrderMapper orderMapper;

    public OrderResponseDto createOrder(OrderDTO orderDTO) {
        Order order = orderRepository.save(
                orderMapper.toOrder(orderDTO));
        return orderMapper.toResponseOrderDTO(order);
    }

    public Order findOrderById(long id) {
        if (orderRepository.existsById(id)) {
            return orderRepository.findById(id).get();
        } else {
            throw new OrderNotFoundException("No order founded with this id: " + id);
        }
    }

    public List<OrderDTO> findOrdersByUserId(long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        if (orders.isEmpty()) {
            throw new OrderNotFoundException("No orders found for User id: " + userId);
        }
        return orderMapper.toDTO(orders);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(long id, OrderDTO orderDTO) {
        Order orderToUpdate = orderRepository.findById(id).get();
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Vehicle vehicle = vehicleRepository.findById(orderDTO.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
        orderToUpdate.setId(id);
        orderToUpdate.setUser(user);
        orderToUpdate.setVehicle(vehicle);
        orderToUpdate.setOrderStatus(orderDTO.getOrderStatus());
        orderToUpdate.setDeposit(orderDTO.getDeposit());
        orderToUpdate.setPayed(orderDTO.isPayed());
        return orderRepository.save(orderToUpdate);
    }

    public void deleteOrderById(long id) {
        orderRepository.deleteById(id);
    }

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

    public Order updateOrderStatus(long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId).get();
        order.setOrderStatus(orderStatus);
        return order;
    }
}

