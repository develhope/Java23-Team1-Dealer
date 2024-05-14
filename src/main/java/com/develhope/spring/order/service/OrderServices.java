package com.develhope.spring.order.service;

import com.develhope.spring.order.repository.OrderRepository;
import com.develhope.spring.vehicles.entity.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServices {
    @Autowired
    private OrderRepository orderRepository;
}
