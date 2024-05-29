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
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable long id) {
        return orderService.findOrderById(id);
    }

    @GetMapping
    public List<Order> findAllOrders() {
        return orderService.findAllOrders();
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable long id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable long id) {
        orderService.deleteOrderById(id);
    }

    @DeleteMapping
    public void deleteAllOrders() {
        orderService.deleteAllOrders();
    }
}