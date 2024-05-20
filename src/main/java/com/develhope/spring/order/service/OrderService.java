package com.develhope.spring.order.service;

import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order post (Order order) {
        return orderRepository.save(order);
    }

}
