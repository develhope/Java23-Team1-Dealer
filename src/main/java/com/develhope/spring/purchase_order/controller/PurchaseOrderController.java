package com.develhope.spring.purchase_order.controller;

import com.develhope.spring.purchase_order.dto.PurchaseOrderCreationDTO;
import com.develhope.spring.purchase_order.dto.PurchaseOrderResponseDTO;
import com.develhope.spring.purchase_order.entity.PurchaseOrder;
import com.develhope.spring.purchase_order.entity.PurchaseOrderStatus;
import com.develhope.spring.purchase_order.service.PurchaseOrderService;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.service.NecessaryAuthority;
import com.develhope.spring.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase_order")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<PurchaseOrderResponseDTO> create(@RequestBody PurchaseOrderCreationDTO purchaseOrderCreationDTO) {
        return ResponseEntity.ok(purchaseService.createOrder(purchaseOrderCreationDTO));
    }

    @GetMapping("/{id}")
    public PurchaseOrder findById(@PathVariable long id) {
        return purchaseService.findOrderById(id);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PurchaseOrder>> findVehiclesByUserId(@PathVariable long userId) {
        return ResponseEntity.ok().body(purchaseService.findOrdersByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> findAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(purchaseService.findAllOrders());
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> findAllByUserId() {
        if (NecessaryAuthority
                .of(UserKind.SELLER, UserKind.ADMIN)
                .check())
        {
            return ResponseEntity.ok(purchaseService.findAllOrders());
        } else {
            return ResponseEntity.ok(purchaseService.findOrdersByUserId(
                    userService.loggedInUser().getId()
            ));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrder> update(@PathVariable long id, @RequestBody PurchaseOrderCreationDTO purchaseOrderCreationDTO) {
        return ResponseEntity.ok().body(purchaseService.updateOrder(id, purchaseOrderCreationDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        purchaseService.deleteOrderById(id);
        return ResponseEntity.ok().body("Deleted");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        purchaseService.deleteAllOrders();
        return ResponseEntity.ok().body("All orderse where deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PurchaseOrder> updateOrderStatus (@PathVariable long id, @RequestParam PurchaseOrderStatus purchaseOrderStatus) {
        return ResponseEntity.ok().body(purchaseService.updateOrderStatus(id, purchaseOrderStatus));
    }
}