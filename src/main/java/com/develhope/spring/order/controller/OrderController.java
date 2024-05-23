package com.develhope.spring.order.controller;

import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order insertOrder(@RequestBody Order order) {
        return orderService.insert(order);
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById (@RequestParam long id) {
        return orderService.get(id);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById (@RequestParam long id) {
        orderService.delete(id);
    }

    @DeleteMapping
    public void deleteAllOrders () {
        orderService.deleteAll();
    }


}
