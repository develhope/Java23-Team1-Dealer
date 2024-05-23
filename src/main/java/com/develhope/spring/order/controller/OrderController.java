package com.develhope.spring.order.controller;

import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order postOrder (@RequestBody Order order) {
        return orderService.post(order);
    }
}
