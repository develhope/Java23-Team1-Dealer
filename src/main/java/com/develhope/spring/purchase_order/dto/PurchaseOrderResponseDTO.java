package com.develhope.spring.purchase_order.dto;

import com.develhope.spring.purchase_order.entity.PurchaseOrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;

@Data
@Service
public class PurchaseOrderResponseDTO {
    private long id;
    private long buyerId;
    private double deposit;
    private boolean payed;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus purchaseOrderStatus;
    private long vehicleId;
    private long sellerId;
}
