package com.develhope.spring.order.service;

import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.repository.OrderRepository;
import com.develhope.spring.exception.OrderNotFoundException;
import com.develhope.spring.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private User user;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order findOrderById(long id) {
        if (orderRepository.existsById(id)) {
            return orderRepository.findById(id).get();
        } else {
            throw new OrderNotFoundException("No order founded with this id: " + id);
        }
    }

    public Optional<Order> findOrderByUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID provided");
        }

        if (orderRepository.existsByUserId(userId)) {
            return orderRepository.findOrderByUserId(userId);
        } else {
            throw new OrderNotFoundException("No order found for this user ID: " + userId);
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
