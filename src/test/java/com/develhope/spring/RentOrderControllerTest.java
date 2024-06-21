package com.develhope.spring;

import com.develhope.spring.exception.customException.BadVehicleStateException;
import com.develhope.spring.exception.customException.OrderNotFoundException;
import com.develhope.spring.exception.customException.UserWithoutPrivilegeException;
import com.develhope.spring.rent_order.controller.RentOrderController;
import com.develhope.spring.rent_order.dto.RentOrderCreationDTO;
import com.develhope.spring.rent_order.dto.RentOrderResponseDTO;
import com.develhope.spring.rent_order.service.RentOrderService;
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
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
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
    private RentOrderController rentController;
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
            true, VehicleState.RENTABLE);

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
            3, new Date(15 / 7 / 2024), new Date(20 / 7 / 2024), 30, 150, 3, 2);
    private final static RentOrderCreationDTO UPDATED_RENT_CREATION_DTO = new RentOrderCreationDTO(
            3, new Date(15 / 7 / 2024), new Date(20 / 7 / 2024), 50, 250, 3, 2);
    private final static RentOrderCreationDTO VALID_RENT_CREATION_DTO2 = new RentOrderCreationDTO(
            1, new Date(12 / 2 / 2024), new Date(17 / 2 / 2024), 30, 150, 2, 2);
    private final static RentOrderCreationDTO INVALID_SELLER_RENT_CREATION_DTO = new RentOrderCreationDTO(
            3, new Date(15 / 7 / 2024), new Date(20 / 7 / 2024), 30, 150, 3, 3);
    private final static RentOrderCreationDTO INVALID_VEHICLE_RENT_CREATION_DTO = new RentOrderCreationDTO(
            3, new Date(15 / 7 / 2024), new Date(20 / 7 / 2024), 30, 150, 1, 2);

    @BeforeEach
    public void setup() throws Exception {
        userService.create(USER_REGISTRATION_ADMIN);
        userService.create(USER_REGISTRATION_SELLER);
        userService.create(USER_REGISTRATION_BUYER);
        vehicleService.createVehicle(DEFAULT_VEHICLE1);
        vehicleService.createVehicle(DEFAULT_VEHICLE2);
        vehicleService.createVehicle(DEFAULT_VEHICLE3);
        vehicleService.createVehicle(DEFAULT_VEHICLE4);
        User response = userService.authenticate(USER_LOGIN_ADMIN);
        adminJwtToken = jwtService.generateToken(response);
    }

    @Test
    @DirtiesContext
    void createRentTest_withValidRent() throws Exception {
        MvcResult result = mockMvc.perform(post("/rent_order")
                        .header("Authorization", "Bearer " + adminJwtToken)
                        .content(objectMapper.writeValueAsString(VALID_RENT_CREATION_DTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        RentOrderResponseDTO rentResult = objectMapper.readValue(responseBody, RentOrderResponseDTO.class);
        assertEquals(rentResult.getId(), 1);
    }

    @Test
    @DirtiesContext
    void createRentTest_withInvalidSeller() throws Exception {
        mockMvc.perform(post("/rent_order")
                        .header("Authorization", "Bearer " + adminJwtToken)
                        .content(objectMapper.writeValueAsString(INVALID_SELLER_RENT_CREATION_DTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    @DirtiesContext
    void createRentTest_withInvalidVehicle() throws Exception {
        mockMvc.perform(post("/rent_order")
                        .header("Authorization", "Bearer " + adminJwtToken)
                        .content(objectMapper.writeValueAsString(INVALID_VEHICLE_RENT_CREATION_DTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DirtiesContext
    void findRentByIdTest_withValidId() throws Exception {
        rentController.create(VALID_RENT_CREATION_DTO);
        MvcResult result = mockMvc.perform(get("/rent_order/1")
                        .header("Authorization", "Bearer " + adminJwtToken))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        RentOrderResponseDTO rentResult = objectMapper.readValue(responseBody, RentOrderResponseDTO.class);
        assertEquals(rentResult.getId(), 1);
    }

    @Test
    @DirtiesContext
    void findRentByIdTest_withInvalidId() throws Exception {
        mockMvc.perform(get("/rent_order/1")
                        .header("Authorization", "Bearer " + adminJwtToken))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @DirtiesContext
    void findAllRentsTest() throws Exception {
        rentController.create(VALID_RENT_CREATION_DTO);
        rentController.create(VALID_RENT_CREATION_DTO2);
        MvcResult result = mockMvc.perform(get("/rent_order")
                        .header("Authorization", "Bearer " + adminJwtToken))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        List<RentOrderResponseDTO> rentListResult = objectMapper.readValue(responseBody, List.class);
        assertEquals(rentListResult.size(), 2);
    }

    @Test
    @DirtiesContext
    void updateRentTest() throws Exception {
        rentController.create(VALID_RENT_CREATION_DTO);
        MvcResult result = mockMvc.perform(put("/rent_order/1")
                        .header("Authorization", "Bearer " + adminJwtToken)
                        .content(objectMapper.writeValueAsString(UPDATED_RENT_CREATION_DTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        RentOrderResponseDTO rentResult = objectMapper.readValue(responseBody, RentOrderResponseDTO.class);
        assertEquals(rentResult.getDailyPrice(), 50);
        assertEquals(rentResult.getTotalPrice(), 250);
    }

    @Test
    @DirtiesContext
    void deleteRentById_withValidId() throws Exception {
        rentController.create(VALID_RENT_CREATION_DTO);
        MvcResult result = mockMvc.perform(delete("/rent_order/1")
                        .header("Authorization", "Bearer " + adminJwtToken))
                .andExpect(status().isOk())
                .andReturn();

        List<RentOrderResponseDTO> rentList = rentService.findAllRents();
        assertEquals(rentList.size(), 0);
    }

    @Test
    @DirtiesContext
    void deleteRentById_withInvalidId() throws Exception {
        mockMvc.perform(delete("/rent_order/1")
                        .header("Authorization", "Bearer " + adminJwtToken))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @DirtiesContext
    void deleteAllOrdersTest() throws Exception {
        rentController.create(VALID_RENT_CREATION_DTO);
        rentController.create(VALID_RENT_CREATION_DTO2);
        MvcResult result = mockMvc.perform(delete("/rent_order")
                        .header("Authorization", "Bearer " + adminJwtToken))
                .andExpect(status().isOk())
                .andReturn();

        List<RentOrderResponseDTO> rentList = rentService.findAllRents();
        assertEquals(rentList.size(), 0);
    }

}
