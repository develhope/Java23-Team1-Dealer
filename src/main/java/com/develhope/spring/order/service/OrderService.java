package com.develhope.spring.order.service;

import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.entity.OrderStatus;
import com.develhope.spring.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order insert(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getById(long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public void deleteById(long id) {
        orderRepository.deleteById(id);
    }

    public void deleteAll() {
        orderRepository.deleteAll();
    }

    public void updateOrderStatus(long orderId) {
        Order order = orderRepository.findById(orderId).get();
        if (order.getOrderStatus() == null) {
            throw new IllegalArgumentException("order status can't be null");
        } else if (order.getOrderStatus() == OrderStatus.ORDERED) {
            order.setOrderStatus(OrderStatus.PURCHASED);
        } else if (order.getOrderStatus() == OrderStatus.PURCHASED) {
            order.setOrderStatus(OrderStatus.ORDERED);
        }


    }

}

