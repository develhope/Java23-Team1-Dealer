package com.develhope.spring.order.service;

import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order insert (Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getById (long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAll () {
        return orderRepository.findAll();
    }

    public void deleteById (long id) {
        orderRepository.deleteById(id);
    }

    public void deleteAll () {
        orderRepository.deleteAll();
    }
}
