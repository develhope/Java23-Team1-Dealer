package com.develhope.spring.rent_order.dto;

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
public class RentOrderCreationDTO {
    private long userId;
    @Temporal(TemporalType.DATE)
    private Date startRent, stopRent;
    private double dailyPrice, totalPrice;
    @Column(nullable = false)
    private long vehicleId;
    private long sellerId;

}
