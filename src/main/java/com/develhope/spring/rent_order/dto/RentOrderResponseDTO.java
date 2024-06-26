package com.develhope.spring.rent_order.dto;

import com.develhope.spring.rent_order.entity.RentOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentOrderResponseDTO {
    private long id;
    private long buyerId;
    @Temporal(TemporalType.DATE)
    private Date startRent, stopRent;
    private double dailyPrice, totalPrice;
    private boolean paid;
    @Enumerated(EnumType.STRING)
    private RentOrderStatus rentOrderStatus;
    private long vehicleId;
    private long sellerId;
}
