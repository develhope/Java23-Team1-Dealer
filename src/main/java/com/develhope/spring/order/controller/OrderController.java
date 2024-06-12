package com.develhope.spring.order.controller;

import com.develhope.spring.order.dto.OrderDTO;
import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.entity.OrderStatus;
import com.develhope.spring.order.service.OrderService;
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

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(orderService.findOrderById(id));
    }

    @GetMapping("/vehicles/user/{userId}")
    public List<OrderDTO> findVehiclesByUserId(@PathVariable long userId) {
        return orderService.findOrdersByUserId(userId);
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(orderService.findAllOrders());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable long id, @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok().body(orderService.updateOrder(id, orderDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        orderService.deleteAllOrders();
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Order> updateOrderStatus (@PathVariable long id, @RequestParam OrderStatus orderStatus) {
        return ResponseEntity.ok().body(orderService.updateOrderStatus(id, orderStatus));
    }
}