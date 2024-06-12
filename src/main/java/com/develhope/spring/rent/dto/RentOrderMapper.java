package com.develhope.spring.rent.dto;

import com.develhope.spring.exception.customException.UserNotFoundException;
import com.develhope.spring.exception.customException.UserWithoutPrivilegeException;
import com.develhope.spring.exception.customException.VehicleNotFoundException;
import com.develhope.spring.rent.entity.RentOrder;
import com.develhope.spring.rent.entity.RentOrderStatus;
import com.develhope.spring.user.entity.UserKind;
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
        rentOrderEntity.setId(rentOrderCreationDTO.getId());
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
        rentOrderEntity.setPayed(false);
        rentOrderEntity.setRentOrderStatus(RentOrderStatus.IN_CREATION);
        return rentOrderEntity;
    }

    public RentOrderCreationDTO toRentOrderCreationDTO(RentOrder rentOrder) {
        RentOrderCreationDTO rentOrderCreationDTO = new RentOrderCreationDTO();
        rentOrderCreationDTO.setId(rentOrder.getId());
        rentOrderCreationDTO.setUserId(rentOrder.getUser().getId());
        rentOrderCreationDTO.setStartRent(rentOrder.getStartRent());
        rentOrderCreationDTO.setStopRent(rentOrder.getStopRent());
        rentOrderCreationDTO.setDailyPrice(rentOrder.getDailyPrice());
        rentOrderCreationDTO.setTotalPrice(rentOrder.getTotalPrice());
        rentOrderCreationDTO.setVehicleId(rentOrder.getVehicle().getId());
        rentOrderCreationDTO.setSellerId(rentOrder.getSeller().getId());
        return rentOrderCreationDTO;
    }

}
