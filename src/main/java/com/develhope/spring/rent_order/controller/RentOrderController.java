package com.develhope.spring.rent_order.controller;

import com.develhope.spring.rent_order.dto.RentOrderCreationDTO;
import com.develhope.spring.rent_order.dto.RentOrderResponseDTO;
import com.develhope.spring.rent_order.entity.RentOrder;
import com.develhope.spring.rent_order.service.RentOrderService;
import com.develhope.spring.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent_order")
public class RentOrderController {
    @Autowired
    private RentOrderService rentService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<RentOrderResponseDTO> create(@RequestBody RentOrderCreationDTO rentOrderCreationDTO) {
        return ResponseEntity.ok().body(rentService.createRent(rentOrderCreationDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentOrderResponseDTO> findById(@PathVariable long id) {
        return ResponseEntity.ok().body(rentService.findRentById(id));
    }

    @GetMapping
    public ResponseEntity<List<RentOrderResponseDTO>> findAll() {
        return ResponseEntity.ok().body(rentService.findAllRents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentOrderResponseDTO> update(@PathVariable long id, @RequestBody RentOrderCreationDTO rentOrderCreationDTO) {
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
