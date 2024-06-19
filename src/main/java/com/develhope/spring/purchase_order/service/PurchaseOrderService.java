package com.develhope.spring.purchase_order.service;

import com.develhope.spring.exception.customException.UserNotFoundException;
import com.develhope.spring.exception.customException.VehicleNotFoundException;
import com.develhope.spring.purchase_order.dto.PurchaseOrderCreationDTO;
import com.develhope.spring.purchase_order.dto.PurchaseOrderMapper;
import com.develhope.spring.purchase_order.dto.PurchaseOrderResponseDTO;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.purchase_order.entity.PurchaseOrder;
import com.develhope.spring.purchase_order.entity.PurchaseOrderStatus;
import com.develhope.spring.purchase_order.repository.PurchaseOrderRepository;
import com.develhope.spring.user.repository.UserRepository;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import com.develhope.spring.exception.customException.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private PurchaseOrderMapper purchaseMapper;

    public PurchaseOrderResponseDTO createOrder(PurchaseOrderCreationDTO purchaseOrderCreationDTO) {
        PurchaseOrder purchaseOrder = purchaseRepository.save(
                purchaseMapper.toPurchaseOrder(purchaseOrderCreationDTO));
        return purchaseMapper.toPurchaseOrderResponseDTO(purchaseOrder);
    }

    public PurchaseOrder findOrderById(long id) {
        if (purchaseRepository.existsById(id)) {
            return purchaseRepository.findById(id).get();
        } else {
            throw new OrderNotFoundException("No purchase_order founded with this id: " + id);
        }
    }

    public List<PurchaseOrder> findAllOrders() {
        return purchaseRepository.findAll();
    }

    public PurchaseOrder updateOrder(long id, PurchaseOrderCreationDTO purchaseOrderCreationDTO) {
        PurchaseOrder purchaseOrderToUpdate = purchaseRepository.findById(id).get();
        User user = userRepository.findById(purchaseOrderCreationDTO.getBuyerId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Vehicle vehicle = vehicleRepository.findById(purchaseOrderCreationDTO.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
        purchaseOrderToUpdate.setId(id);
        purchaseOrderToUpdate.setBuyer(user);
        purchaseOrderToUpdate.setVehicle(vehicle);
        purchaseOrderToUpdate.setPurchaseOrderStatus(purchaseOrderCreationDTO.getPurchaseOrderStatus());
        purchaseOrderToUpdate.setDeposit(purchaseOrderCreationDTO.getDeposit());
        purchaseOrderToUpdate.setPayed(purchaseOrderCreationDTO.isPayed());
        return purchaseRepository.save(purchaseOrderToUpdate);
    }

    public void deleteOrderById(long id) {
        purchaseRepository.deleteById(id);
    }

    public void deleteAllOrders() {
        purchaseRepository.deleteAll();
    }

    public PurchaseOrder updateOrderStatus(long orderId, PurchaseOrderStatus purchaseOrderStatus) {
        PurchaseOrder purchaseOrder = purchaseRepository.findById(orderId).get();
        purchaseOrder.setPurchaseOrderStatus(purchaseOrderStatus);
        return purchaseOrder;
    }
}

