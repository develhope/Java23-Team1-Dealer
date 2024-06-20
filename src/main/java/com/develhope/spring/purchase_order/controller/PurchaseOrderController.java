package com.develhope.spring.purchase_order.controller;

import com.develhope.spring.purchase_order.dto.PurchaseOrderCreationDTO;
import com.develhope.spring.purchase_order.dto.PurchaseOrderMapper;
import com.develhope.spring.purchase_order.dto.PurchaseOrderResponseDTO;
import com.develhope.spring.purchase_order.entity.PurchaseOrder;
import com.develhope.spring.purchase_order.entity.PurchaseOrderStatus;
import com.develhope.spring.purchase_order.repository.PurchaseOrderRepository;
import com.develhope.spring.purchase_order.service.PurchaseOrderFilterService;
import com.develhope.spring.purchase_order.service.PurchaseOrderService;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.service.NecessaryAuthority;
import com.develhope.spring.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @PostMapping
    public ResponseEntity<PurchaseOrderResponseDTO> create(@RequestBody PurchaseOrderCreationDTO purchaseOrderCreationDTO) {
        return ResponseEntity.ok(purchaseService.createOrder(purchaseOrderCreationDTO));
    }

    @GetMapping("/{id}")
    public PurchaseOrder findById(@PathVariable long id) {
        return purchaseService.findOrderById(id);
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<List<PurchaseOrderResponseDTO>> filterPurchaseOrder(
            @RequestBody PurchaseOrderFilterService purchaseOrderFilterService) {
        purchaseOrderFilterService.setPurchaseOrderRepository(purchaseOrderRepository);
        if (NecessaryAuthority.of(UserKind.BUYER).check()) {
            purchaseOrderFilterService.setBuyerId(
                    userService.loggedInUser().getId());
        }

        return ResponseEntity
                .ok(purchaseService.filterPurchaseOrder(purchaseOrderFilterService));
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
    public ResponseEntity<PurchaseOrder> updateOrderStatus(@PathVariable long id, @RequestParam PurchaseOrderStatus purchaseOrderStatus) {
        return ResponseEntity.ok().body(purchaseService.updateOrderStatus(id, purchaseOrderStatus));
    }
}