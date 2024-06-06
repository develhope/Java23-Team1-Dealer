package com.develhope.spring.order.dto;

import com.develhope.spring.order.entity.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
public class OrderDTO {

    private long userId;

    private double deposit;

    private boolean payed;

    private long vehicleId;
}
