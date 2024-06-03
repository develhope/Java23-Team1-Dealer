package com.develhope.spring.order.controller;

import com.develhope.spring.exception.OrderNotFoundException;
import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.service.OrderService;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/shop/{userId}")
    public ResponseEntity<String> shopVehicle(@RequestBody Vehicle vehicle, @PathVariable User userId, @RequestParam VehicleState isPurchasable) {
        try {
            Order order = orderService.shopVehicle(vehicle, isPurchasable, userId);
            return ResponseEntity.ok("Vehicle has been successfully purchased! Order ID: " + order.getId());
        } catch (OrderNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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