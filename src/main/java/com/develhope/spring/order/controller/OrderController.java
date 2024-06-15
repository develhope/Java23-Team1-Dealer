package com.develhope.spring.order.controller;

import com.develhope.spring.order.dto.OrderDTO;
import com.develhope.spring.order.dto.OrderResponseDto;
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
    public ResponseEntity<OrderResponseDto> create(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable long id) {
        return orderService.findOrderById(id);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> findVehiclesByUserId(@PathVariable long userId) {
        return ResponseEntity.ok().body(orderService.findOrdersByUserId(userId));
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
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok().body("Deleted");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        orderService.deleteAllOrders();
        return ResponseEntity.ok().body("All orderse where deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Order> updateOrderStatus (@PathVariable long id, @RequestParam OrderStatus orderStatus) {
        return ResponseEntity.ok().body(orderService.updateOrderStatus(id, orderStatus));
    }
}