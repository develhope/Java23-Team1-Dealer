package com.develhope.spring.rent.dto;

import com.develhope.spring.exception.customException.UserNotFoundException;
import com.develhope.spring.exception.customException.VehicleNotFoundException;
import com.develhope.spring.rent.entity.RentOrder;
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
                        "User with id " + rentOrderCreationDTO.getUserId() + " not found")));
        rentOrderEntity.setVehicle(vehicleRepository.findById(rentOrderCreationDTO.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException(
                        "Vehicle with id " + rentOrderCreationDTO.getVehicleId() + " not found")));
    }
}
