package com.develhope.spring.rent.controller;

import com.develhope.spring.rent.dto.RentOrderCreationDTO;
import com.develhope.spring.rent.entity.RentOrder;
import com.develhope.spring.rent.service.RentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentOrderController {
    @Autowired
    private RentOrderService rentService;

    @PostMapping
    public ResponseEntity<RentOrder> create(@RequestBody RentOrderCreationDTO rentOrderCreationDTO) {
        return ResponseEntity.ok().body(rentService.createRent(rentOrderCreationDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentOrder> findById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(rentService.findRentById(id));
    }

    @GetMapping
    public ResponseEntity<List<RentOrder>> findAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(rentService.findAllRents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentOrder> update(@PathVariable long id, @RequestBody RentOrderCreationDTO rentOrderCreationDTO) {
        return ResponseEntity.ok().body(rentService.updateRent(id, rentOrderCreationDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        rentService.deleteRentById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        rentService.deleteAllOrders();
        return ResponseEntity.ok().build();
    }

}
