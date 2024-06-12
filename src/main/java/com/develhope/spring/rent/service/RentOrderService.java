package com.develhope.spring.rent.service;

import com.develhope.spring.exception.customException.OrderNotFoundException;
import com.develhope.spring.exception.customException.UserWithoutPrivilegeException;
import com.develhope.spring.rent.dto.RentOrderCreationDTO;
import com.develhope.spring.rent.dto.RentOrderMapper;
import com.develhope.spring.rent.entity.RentOrder;
import com.develhope.spring.rent.entity.RentOrderStatus;
import com.develhope.spring.rent.repository.RentRepository;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.repository.UserRepository;
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

    public RentOrder createRent(RentOrderCreationDTO rentOrderCreationDTO) {
        RentOrder rentEntity = rentMapper.toRentOrder(rentOrderCreationDTO);
        if (rentEntity.getSeller().getUserKind() != UserKind.SELLER) {
            throw new UserWithoutPrivilegeException("Seller is not authorized");
        }
        rentEntity.setRentOrderStatus(RentOrderStatus.ACCEPTED);
        return rentRepository.save(rentEntity);
        // da aggiungere anche un check sul pagamento, in modo da impostare "payed" a true.
        // il totalPrice va impostato in base a startRent e stopRent.
    }

    public RentOrder findRentById(long id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("No order founded with this id: " + id));
    }

    public List<RentOrder> findAllRents () {
        return rentRepository.findAll();
    }

    public RentOrder updateRent (long id, RentOrderCreationDTO rentOrderCreationDTO) {
        RentOrder rentToUpdate = rentRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("No order founded with this id: " + id));
        rentToUpdate.setId(id);
        rentToUpdate = rentMapper.toRentOrder(rentOrderCreationDTO);
        rentToUpdate.setId(id);
        rentToUpdate.setRentOrderStatus(RentOrderStatus.ACCEPTED);
        return rentRepository.save(rentToUpdate);
        // da aggiungere anche un check sul pagamento, in modo da impostare "payed" a true.
        // il totalPrice va impostato in base a startRent e stopRent.
    }

    public void deleteRentById (long id) {
        if (rentRepository.existsById(id)) {
            rentRepository.deleteById(id);
        } else {
            throw new OrderNotFoundException("No order founded with this id: " + id);
        }
    }

    public void deleteAllOrders () {
        rentRepository.deleteAll();
    }
}