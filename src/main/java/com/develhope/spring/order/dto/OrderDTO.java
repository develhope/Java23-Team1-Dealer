package com.develhope.spring.order.dto;

import com.develhope.spring.order.entity.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private long userId;
    private double deposit;
    private boolean payed;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private long vehicleId;
}
