package com.develhope.spring;

import com.develhope.spring.order.dto.OrderDTO;
import com.develhope.spring.order.dto.OrderResponseDto;
import com.develhope.spring.order.entity.OrderStatus;
import com.develhope.spring.order.service.OrderService;
import com.develhope.spring.user.dto.LoginDto;
import com.develhope.spring.user.dto.RegistrationDto;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.service.JwtService;
import com.develhope.spring.user.service.UserService;
import com.develhope.spring.vehicles.entity.*;
import com.develhope.spring.vehicles.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@Profile("test")
@AutoConfigureMockMvc
public class OrderTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ObjectMapper objectMapper;

    String jwtToken;


    private static final RegistrationDto registrationDto = new RegistrationDto(
            "Gianni", "Rossi", "53434343", "e.k@g.it", "gianni", "root", UserKind.ADMIN
    );

    private static final Vehicle vehicle = new Vehicle(
            0, VehicleKind.CAR, "Tesla", "m1,", 50, "Blue",
            40, Gearbox.AUTOMATIC, 2011, FuelType.BATTERY, 10000,
            false, 0, "", true, VehicleState.PURCHASABLE);

    private static final OrderDTO orderDTO = new OrderDTO(
            1, 100, false, OrderStatus.ORDERED, 1,1
    );


    @BeforeEach
    void setUp() throws Exception {
        if (!userService.existsById(1L)) userService.create(registrationDto);
        if (!vehicleService.existsById(1L)) vehicleService.createVehicle(vehicle);

        User response = userService.authenticate(
                new LoginDto(registrationDto.getUsername(), registrationDto.getPassword()));
        assertNotNull(response);
        jwtToken = jwtService.generateToken(response);

    }

    @Test
    void createOrder() throws Exception {

       MvcResult result = mockMvc.perform(post("/order")
                .header("Authorization", "Bearer " + jwtToken)
                .content(objectMapper.writeValueAsString(orderDTO))
                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        OrderResponseDto orderReturned = objectMapper.readValue(responseBody, OrderResponseDto.class);
        assertNotNull(orderReturned);
    }

}
