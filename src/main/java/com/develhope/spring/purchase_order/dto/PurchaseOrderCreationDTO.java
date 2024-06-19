package com.develhope.spring.purchase_order.dto;

import com.develhope.spring.purchase_order.entity.PurchaseOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderCreationDTO {
    private long buyerId;
    private double deposit;
    private boolean payed;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus purchaseOrderStatus;
    private long vehicleId;
    private long sellerId;
}
