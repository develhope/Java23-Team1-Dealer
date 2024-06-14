package com.develhope.spring.order.dto;

import com.develhope.spring.order.entity.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;

@Data
@Service
public class OrderResponseDto {
    private long id;
    private long userId;
    private double deposit;
    private boolean payed;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private long vehicleId;
    private long sellerId;
}
