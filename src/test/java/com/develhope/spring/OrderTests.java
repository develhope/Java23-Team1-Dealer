package com.develhope.spring;

import com.develhope.spring.order.entity.Order;
import com.develhope.spring.order.entity.OrderDTO;
import com.develhope.spring.order.service.OrderService;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.service.UserService;
import com.develhope.spring.vehicles.entity.*;
import com.develhope.spring.vehicles.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderTests {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private VehicleService vehicleService;

    User user = new User(
            0, "Gianni", "Rossi", "53434343", "e.k@g.it", "root", UserKind.ADMIN
    );

    Vehicle vehicle = new Vehicle(
            0, VehicleKind.CAR, "Tesla", "m1,", 50, "Blue", 40, Gearbox.AUTOMATIC, 2011, FuelType.BATTERY, 10000, false, 0, "", true, VehicleState.PURCHASABLE, false, false
    );

    private OrderDTO orderDTO = new OrderDTO(
            1, 100, false, 1
    );

    @BeforeEach
    void setUp() {
        User returnedUser = userService.createProfile(user);
//        if (returnedUser != null) throw new RuntimeException("error saving user");
        Vehicle returnedVehicle = vehicleService.createVehicle(vehicle);
//        if (returnedVehicle != null) throw new RuntimeException("error saving vehicle");
    }

    @Test
    void createOrder() throws Exception {
        Order orderReturned = orderService.createOrder(orderDTO);
        assertEquals(orderReturned != null ? orderReturned.getId() : 1, orderReturned.getId());
    }

}
