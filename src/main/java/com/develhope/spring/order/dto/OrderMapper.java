package com.develhope.spring.order.dto;

import com.develhope.spring.order.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderStatus(order.getOrderStatus());
        dto.setVehicleId(order.getUser().getId());
        dto.setUserId(order.getUser().getId());
        return dto;
    }

    public static List<OrderDTO> toDTO(List<Order> orders) {
        return orders.stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }
}
