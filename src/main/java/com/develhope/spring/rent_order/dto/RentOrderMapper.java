package com.develhope.spring.rent_order.dto;

import com.develhope.spring.exception.customException.UserNotFoundException;
import com.develhope.spring.exception.customException.VehicleNotFoundException;
import com.develhope.spring.rent_order.entity.RentOrder;
import com.develhope.spring.rent_order.entity.RentOrderStatus;
import com.develhope.spring.user.repository.UserRepository;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class RentOrderMapper {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    public RentOrder toRentOrder (RentOrderCreationDTO rentOrderCreationDTO) {
        RentOrder rentOrderEntity = new RentOrder();
        rentOrderEntity.setUser(userRepository.findById(rentOrderCreationDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id: " + rentOrderCreationDTO.getUserId() + " not found")));
        rentOrderEntity.setVehicle(vehicleRepository.findById(rentOrderCreationDTO.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException(
                        "Vehicle with id: " + rentOrderCreationDTO.getVehicleId() + " not found")));
        rentOrderEntity.setSeller(userRepository.findById(rentOrderCreationDTO.getSellerId())
                .orElseThrow(() -> new UserNotFoundException(
                        "Seller with id: " + rentOrderCreationDTO.getSellerId() + " not found")));
        rentOrderEntity.setStartRent(rentOrderCreationDTO.getStartRent());
        rentOrderEntity.setStopRent(rentOrderCreationDTO.getStopRent());
        rentOrderEntity.setDailyPrice(rentOrderCreationDTO.getDailyPrice());
        rentOrderEntity.setTotalPrice(rentOrderCreationDTO.getTotalPrice());
        rentOrderEntity.setPaid(false);
        rentOrderEntity.setRentOrderStatus(RentOrderStatus.IN_CREATION);
        return rentOrderEntity;
    }

    public RentOrderResponseDTO toRentOrderResponseDTO(RentOrder rentOrder) {
        RentOrderResponseDTO rentOrderResponseEntity = new RentOrderResponseDTO();
        rentOrderResponseEntity.setId(rentOrder.getId());
        rentOrderResponseEntity.setUserId(rentOrder.getUser().getId());
        rentOrderResponseEntity.setStartRent(rentOrder.getStartRent());
        rentOrderResponseEntity.setStopRent(rentOrder.getStopRent());
        rentOrderResponseEntity.setDailyPrice(rentOrder.getDailyPrice());
        rentOrderResponseEntity.setTotalPrice(rentOrder.getTotalPrice());
        rentOrderResponseEntity.setPayed(rentOrder.isPaid());
        rentOrderResponseEntity.setRentOrderStatus(rentOrder.getRentOrderStatus());
        rentOrderResponseEntity.setVehicleId(rentOrder.getVehicle().getId());
        rentOrderResponseEntity.setSellerId(rentOrder.getSeller().getId());
        return rentOrderResponseEntity;
    }

}
