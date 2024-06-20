package com.develhope.spring.rent_order.service;

import com.develhope.spring.exception.customException.BadVehicleStateException;
import com.develhope.spring.exception.customException.OrderNotFoundException;
import com.develhope.spring.exception.customException.UserWithoutPrivilegeException;
import com.develhope.spring.rent_order.dto.RentOrderCreationDTO;
import com.develhope.spring.rent_order.dto.RentOrderMapper;
import com.develhope.spring.rent_order.dto.RentOrderResponseDTO;
import com.develhope.spring.rent_order.entity.RentOrder;
import com.develhope.spring.rent_order.entity.RentOrderStatus;
import com.develhope.spring.rent_order.repository.RentRepository;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.repository.UserRepository;
import com.develhope.spring.vehicles.entity.VehicleState;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentOrderService {
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private RentOrderMapper rentMapper;

    public RentOrderResponseDTO createRent(RentOrderCreationDTO rentOrderCreationDTO) {
        RentOrder rentEntity = rentMapper.toRentOrder(rentOrderCreationDTO);
        if (rentEntity.getSeller().getUserKind() != UserKind.SELLER) {
            throw new UserWithoutPrivilegeException("Seller is not authorized");
        }
        if (rentEntity.getVehicle().getVehicleState() != VehicleState.RENTABLE) {
            throw new BadVehicleStateException("This vehicle is not rentable");
        }
        rentEntity.setRentOrderStatus(RentOrderStatus.ACCEPTED);
        rentRepository.save(rentEntity);
        return rentMapper.toRentOrderResponseDTO(rentEntity);
        // da aggiungere anche un check sul pagamento, in modo da impostare "payed" a true.
        // il totalPrice va impostato in base a startRent e stopRent.
    }

    public RentOrderResponseDTO findRentById(long id) {
        RentOrder rent = rentRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("No purchase_order founded with this id: " + id));
        return RentOrderMapper.toRentOrderResponseDTO(rent);
    }

    public List<RentOrderResponseDTO> findAllRents () {
        List<RentOrder> rentList = rentRepository.findAll();
        return rentMapper.toRentOrderResponseDTOList(rentList);
    }

    public RentOrderResponseDTO updateRent (long id, RentOrderCreationDTO rentOrderCreationDTO) {
        RentOrder rentToUpdate = rentRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("No purchase_order founded with this id: " + id));
        rentToUpdate.setId(id);
        rentToUpdate = rentMapper.toRentOrder(rentOrderCreationDTO);
        rentToUpdate.setId(id);
        rentToUpdate.setRentOrderStatus(RentOrderStatus.ACCEPTED);
        rentRepository.save(rentToUpdate);
        return RentOrderMapper.toRentOrderResponseDTO(rentToUpdate);
        // da aggiungere anche un check sul pagamento, in modo da impostare "payed" a true.
        // il totalPrice va impostato in base a startRent e stopRent.
    }

    public void deleteRentById (long id) {
        if (rentRepository.existsById(id)) {
            rentRepository.deleteById(id);
        } else {
            throw new OrderNotFoundException("No purchase_order founded with this id: " + id);
        }
    }

    public void deleteAllOrders () {
        rentRepository.deleteAll();
    }
}