package com.develhope.spring;

import com.develhope.spring.purchase_order.dto.PurchaseOrderResponseDTO;
import com.develhope.spring.rent_order.controller.RentOrderController;
import com.develhope.spring.rent_order.dto.RentOrderCreationDTO;
import com.develhope.spring.rent_order.dto.RentOrderMapper;
import com.develhope.spring.rent_order.dto.RentOrderResponseDTO;
import com.develhope.spring.rent_order.service.RentOrderService;
import com.develhope.spring.user.dto.LoginDto;
import com.develhope.spring.user.dto.RegistrationDto;
import com.develhope.spring.user.entity.LoginResponse;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.service.JwtService;
import com.develhope.spring.user.service.UserService;
import com.develhope.spring.vehicles.entity.*;
import com.develhope.spring.vehicles.service.VehicleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class RentOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RentOrderService rentService;
    @Autowired
    private RentOrderMapper rentMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private VehicleService vehicleService;
    String adminJwtToken;

    private final static RegistrationDto USER_REGISTRATION_ADMIN = new RegistrationDto("Amministratore", "Delegato",
            "3333333333", "admin@gmail.com",
            "admin", "admin", UserKind.ADMIN);
    private final static RegistrationDto USER_REGISTRATION_SELLER = new RegistrationDto("Seller", "SonoIlSeller",
            "2222222222", "seller@gmail.com",
            "seller", "seller", UserKind.SELLER);
    private final static RegistrationDto USER_REGISTRATION_BUYER = new RegistrationDto("Rocco", "Rock",
            "3202020202", "roccorock@gmail.com",
            "buyer", "buyer", UserKind.BUYER);
    private final static LoginDto USER_LOGIN_BUYER = new LoginDto("RoccoRock", "rocco23624");
    private final static LoginDto USER_LOGIN_ADMIN = new LoginDto("admin", "admin");
    private final static Vehicle DEFAULT_VEHICLE1 = new Vehicle(
            0L, VehicleKind.CAR, "Toyota", "Corolla",
            1600, "Blue", 120, Gearbox.MANUAL,
            2020, FuelType.GASOLINE, 15000.0,
            false, 0.0, "Air Conditioning",
            true, VehicleState.RENTED);
    private final static Vehicle DEFAULT_VEHICLE2 = new Vehicle(
            0L, VehicleKind.CAR, "Ford", "Fiesta",
            1800, "Red", 140, Gearbox.MANUAL,
            2020, FuelType.DIESEL, 12500,
            false, 0.0, "Air Conditioning",
            true, VehicleState.PURCHASED);

    private final static Vehicle DEFAULT_VEHICLE3 = new Vehicle(
            0L, VehicleKind.SCOOTER, "Yamaha", "Fuciu",
            750, "Blue", 120, Gearbox.MANUAL,
            2023, FuelType.GASOLINE, 19000.0,
            false, 0.0, "none",
            true, VehicleState.RENTABLE);
    private final static Vehicle DEFAULT_VEHICLE4 = new Vehicle(
            0L, VehicleKind.VAN, "FIAT", "Doblo",
            1200, "White", 100, Gearbox.MANUAL,
            2015, FuelType.BATTERY, 9500.0,
            false, 0.0, "none",
            true, VehicleState.PURCHASABLE);
    private final static RentOrderCreationDTO VALID_RENT_CREATION_DTO = new RentOrderCreationDTO(
            3, new Date(15/ 7 /2024), new Date(20/ 7 /2024),30, 150, 3,2);

    @BeforeEach
    public void setup() throws Exception {
        userService.create(USER_REGISTRATION_ADMIN);
        userService.create(USER_REGISTRATION_SELLER);
        userService.create(USER_REGISTRATION_BUYER);
        vehicleService.createVehicle(DEFAULT_VEHICLE1);
        vehicleService.createVehicle(DEFAULT_VEHICLE2);
        vehicleService.createVehicle(DEFAULT_VEHICLE3);
        vehicleService.createVehicle(DEFAULT_VEHICLE4);
        User response = userService.authenticate(
                new LoginDto(USER_REGISTRATION_ADMIN.getUsername(), USER_REGISTRATION_ADMIN.getPassword()));
        assertNotNull(response);
        adminJwtToken = jwtService.generateToken(response);
    }

    @Test
    @DirtiesContext
    void createRentOrderTest_withValidRent () throws Exception {
        MvcResult result = mockMvc.perform(post("/rent_order")
                        .header("Authorization", "Bearer " + adminJwtToken)
                        .content(objectMapper.writeValueAsString(VALID_RENT_CREATION_DTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        RentOrderResponseDTO rentResult = objectMapper.readValue(responseBody, RentOrderResponseDTO.class);
        assertNotNull(rentResult);
    }

}
