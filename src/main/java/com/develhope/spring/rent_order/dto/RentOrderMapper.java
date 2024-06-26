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

import java.util.List;
import java.util.stream.Collectors;

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
        rentOrderEntity.setUser(userRepository.findById(rentOrderCreationDTO.getBuyerId())
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id: " + rentOrderCreationDTO.getBuyerId() + " not found")));
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

    public static RentOrderResponseDTO toRentOrderResponseDTO(RentOrder rentOrder) {
        RentOrderResponseDTO rentOrderResponseEntity = new RentOrderResponseDTO();
        rentOrderResponseEntity.setId(rentOrder.getId());
        rentOrderResponseEntity.setBuyerId(rentOrder.getUser().getId());
        rentOrderResponseEntity.setStartRent(rentOrder.getStartRent());
        rentOrderResponseEntity.setStopRent(rentOrder.getStopRent());
        rentOrderResponseEntity.setDailyPrice(rentOrder.getDailyPrice());
        rentOrderResponseEntity.setTotalPrice(rentOrder.getTotalPrice());
        rentOrderResponseEntity.setPaid(rentOrder.isPaid());
        rentOrderResponseEntity.setRentOrderStatus(rentOrder.getRentOrderStatus());
        rentOrderResponseEntity.setVehicleId(rentOrder.getVehicle().getId());
        rentOrderResponseEntity.setSellerId(rentOrder.getSeller().getId());
        return rentOrderResponseEntity;
    }

    public List<RentOrderResponseDTO> toRentOrderResponseDTOList (List<RentOrder> rentList) {
        return rentList.stream().map(RentOrderMapper::toRentOrderResponseDTO).collect(Collectors.toList());
    }

}
