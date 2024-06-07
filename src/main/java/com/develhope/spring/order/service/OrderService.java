package com.develhope.spring.order.service;

import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.entity.OrderStatus;
import com.develhope.spring.order.repository.OrderRepository;
import com.develhope.spring.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

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

    public void updateOrderStatus(long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId).get();
        order.setOrderStatus(orderStatus);
    }
}

