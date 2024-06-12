package com.develhope.spring.rent.dto;

import com.develhope.spring.rent.entity.RentOrderStatus;
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
public class RentCreationDTO {
    private long userId;
    @Temporal(TemporalType.DATE)
    private Date startRent, stopRent;
    private double dailyPrice, totalPrice;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RentOrderStatus rentOrderStatus;
    private long vehicleId;
    private long sellerId;
}
