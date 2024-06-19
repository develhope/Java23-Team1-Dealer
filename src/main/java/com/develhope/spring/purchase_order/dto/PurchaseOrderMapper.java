package com.develhope.spring.purchase_order.dto;

import com.develhope.spring.exception.customException.UserNotFoundException;
import com.develhope.spring.exception.customException.VehicleNotFoundException;
import com.develhope.spring.purchase_order.entity.PurchaseOrder;
import com.develhope.spring.user.repository.UserRepository;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@NoArgsConstructor
public class PurchaseOrderMapper {

    @Autowired
    private static UserRepository userRepository;

    @Autowired
    private static VehicleRepository vehicleRepository;

    public static PurchaseOrder toPurchaseOrder(PurchaseOrderCreationDTO purchaseOrderCreationDTO) {
        PurchaseOrder purchaseOrderEntity = new PurchaseOrder();
        purchaseOrderEntity.setUser(
                userRepository.findById(purchaseOrderCreationDTO.getUserId())
                        .orElseThrow(() -> new UserNotFoundException("User not found"))
        );
        purchaseOrderEntity.setVehicle(
                vehicleRepository.findById(purchaseOrderCreationDTO.getVehicleId())
                        .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"))
        );
        purchaseOrderEntity.setCreatedAt(new Date(System.currentTimeMillis()));
        purchaseOrderEntity.setPurchaseOrderStatus(purchaseOrderCreationDTO.getPurchaseOrderStatus());
        purchaseOrderEntity.setDeposit(purchaseOrderCreationDTO.getDeposit());
        purchaseOrderEntity.setPayed(purchaseOrderCreationDTO.isPayed());
        purchaseOrderEntity.setSeller( userRepository.findById(purchaseOrderCreationDTO.getSellerId())
                .orElseThrow(() -> new UserNotFoundException("Seller not found"))
        );

        return purchaseOrderEntity;
    }

    public static List<PurchaseOrder> toDTO(List<PurchaseOrderCreationDTO> orders) {
        return orders.stream()
                .map(PurchaseOrderMapper::toPurchaseOrder)
                .collect(Collectors.toList());
    }

    public PurchaseOrderResponseDTO toPurchaseOrderResponseDTO(PurchaseOrder purchaseOrder) {
        PurchaseOrderResponseDTO purchaseOrderResponseDTO = new PurchaseOrderResponseDTO();
        purchaseOrderResponseDTO.setId(purchaseOrder.getId());
        purchaseOrderResponseDTO.setUserId(purchaseOrder.getUser().getId());
        purchaseOrderResponseDTO.setVehicleId(purchaseOrder.getVehicle().getId());
        purchaseOrderResponseDTO.setCreatedAt(purchaseOrder.getCreatedAt());
        purchaseOrderResponseDTO.setPurchaseOrderStatus(purchaseOrder.getPurchaseOrderStatus());
        purchaseOrderResponseDTO.setDeposit(purchaseOrder.getDeposit());
        purchaseOrderResponseDTO.setPayed(purchaseOrder.isPayed());
        purchaseOrderResponseDTO.setSellerId(purchaseOrder.getSeller().getId());
        return purchaseOrderResponseDTO;
    }
}
