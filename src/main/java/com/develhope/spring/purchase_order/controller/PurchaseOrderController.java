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
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @PostMapping
    public ResponseEntity<PurchaseOrderResponseDTO> create(@RequestBody PurchaseOrderCreationDTO purchaseOrderCreationDTO) {
        return ResponseEntity.ok(purchaseOrderService.createOrder(purchaseOrderCreationDTO));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrderResponseDTO>> filterPurchaseOrder(
            @RequestBody PurchaseOrderFilterService purchaseOrderFilterService) {
        purchaseOrderFilterService.setPurchaseOrderRepository(purchaseOrderRepository);
        if (NecessaryAuthority.of(UserKind.BUYER).check()) {
            purchaseOrderFilterService.setBuyerId(
                    userService.loggedInUser().getId());
        }

        return ResponseEntity
                .ok(purchaseOrderService.filterPurchaseOrder(purchaseOrderFilterService));
    }

    @PutMapping
    public ResponseEntity<PurchaseOrder> update(@RequestParam long id, @RequestBody PurchaseOrderCreationDTO purchaseOrderCreationDTO) {
        return ResponseEntity.ok().body(purchaseOrderService.updateOrder(id, purchaseOrderCreationDTO));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteById(@RequestParam long id) {
        purchaseOrderService.deleteOrderById(id);
        return ResponseEntity.ok().body("Deleted");
    }

    @DeleteMapping(path = "/all")
    public ResponseEntity<String> deleteAll() {
        NecessaryAuthority.of(UserKind.ADMIN).grant();
        purchaseOrderService.deleteAllOrders();
        return ResponseEntity.ok().body("All orderse where deleted");
    }

    @PatchMapping
    public ResponseEntity<PurchaseOrder> updateOrderStatus(@RequestParam long id, @RequestParam PurchaseOrderStatus purchaseOrderStatus) {
        return ResponseEntity.ok().body(purchaseOrderService.updateOrderStatus(id, purchaseOrderStatus));
    }
}