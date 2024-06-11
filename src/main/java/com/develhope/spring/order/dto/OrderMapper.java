package com.develhope.spring.order.dto;

import com.develhope.spring.exception.customException.UserNotFoundException;
import com.develhope.spring.exception.customException.VehicleNotFoundException;
import com.develhope.spring.order.entity.Order;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.user.repository.UserRepository;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Data
@NoArgsConstructor
public class OrderMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public Order toOrder(OrderDTO orderDTO) {
        Order orderEntity = new Order();
        orderEntity.setUser(
                userRepository.findById(orderDTO.getUserId())
                        .orElseThrow(() -> new UserNotFoundException("User not found"))
        );
        orderEntity.setVehicle(
                vehicleRepository.findById(orderDTO.getVehicleId())
                        .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"))
        );
        orderEntity.setCreatedAt(new Date(System.currentTimeMillis()));
        orderEntity.setOrderStatus(orderDTO.getOrderStatus());
        orderEntity.setDeposit(orderDTO.getDeposit());
        orderEntity.setPayed(orderDTO.isPayed());
        orderEntity.setSeller( userRepository.findById(orderDTO.getSellerId())
                .orElseThrow(() -> new UserNotFoundException("Seller not found"))
        );

        return orderEntity;
    }

    public ResponseOrderDto toResponseOrderDTO(Order order) {
        ResponseOrderDto responseOrderDto = new ResponseOrderDto();
        responseOrderDto.setId(order.getId());
        responseOrderDto.setUserId(order.getUser().getId());
        responseOrderDto.setVehicleId(order.getVehicle().getId());
        responseOrderDto.setCreatedAt(order.getCreatedAt());
        responseOrderDto.setOrderStatus(order.getOrderStatus());
        responseOrderDto.setDeposit(order.getDeposit());
        responseOrderDto.setPayed(order.isPayed());
        responseOrderDto.setSellerId(order.getSeller().getId());
        return responseOrderDto;
    }
}
