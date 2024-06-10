package com.develhope.spring;

import com.develhope.spring.order.dto.OrderDTO;
import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.entity.OrderStatus;
import com.develhope.spring.order.service.OrderService;
import com.develhope.spring.user.dto.RegistrationDto;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.service.UserService;
import com.develhope.spring.vehicles.entity.*;
import com.develhope.spring.vehicles.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Profile("test")
public class OrderTests {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private VehicleService vehicleService;

    RegistrationDto registrationDto = new RegistrationDto(
            "Gianni", "Rossi", "53434343", "e.k@g.it", "gianni", "root", UserKind.ADMIN
    );

    Vehicle vehicle = new Vehicle(
            0, VehicleKind.CAR, "Tesla", "m1,", 50, "Blue", 40, Gearbox.AUTOMATIC, 2011, FuelType.BATTERY, 10000, false, 0, "", true, VehicleState.PURCHASABLE, false, false
    );

    private OrderDTO orderDTO = new OrderDTO(
            1, 100, false, OrderStatus.ORDERED, 1
    );

    @BeforeEach
    void setUp() {
        if (!userService.existsById(1L)) userService.create(registrationDto);
        if (!vehicleService.existsById(1L)) vehicleService.createVehicle(vehicle);
    }

    @Test
    void createOrder() throws Exception {
        Order orderReturned = orderService.createOrder(orderDTO);
        assertEquals(orderReturned != null ? orderReturned.getId() : 1, orderReturned.getId());
    }

}
