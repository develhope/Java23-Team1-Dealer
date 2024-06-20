package com.develhope.spring;

import com.develhope.spring.rent_order.service.RentOrderService;
import com.develhope.spring.user.dto.LoginDto;
import com.develhope.spring.user.dto.RegistrationDto;
import com.develhope.spring.user.entity.LoginResponse;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.service.JwtService;
import com.develhope.spring.user.service.UserService;
import com.develhope.spring.vehicles.entity.*;
import com.develhope.spring.vehicles.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
    private UserService userService;
    @Autowired
    private VehicleService vehicleService;
    String adminJwtToken;

    private final static RegistrationDto USER_REGISTRATION_BUYER = new RegistrationDto("Rocco", "Rock",
            "3202020202", "roccorock@gmail.com",
            "RoccoRock", "rocco23624", UserKind.BUYER);
    private final static RegistrationDto USER_REGISTRATION_ADMIN = new RegistrationDto("Amministratore", "Delegato",
            "3333333333", "admin@gmail.com",
            "admin", "admin", UserKind.ADMIN);
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

    @BeforeEach
    public void setup() throws Exception {
        vehicleService.createVehicle(DEFAULT_VEHICLE1);
        vehicleService.createVehicle(DEFAULT_VEHICLE2);
        vehicleService.createVehicle(DEFAULT_VEHICLE3);
        vehicleService.createVehicle(DEFAULT_VEHICLE4);
        userService.create(USER_REGISTRATION_ADMIN);

        MvcResult result = mockMvc.perform(post("/user/login")
                        .content(objectMapper.writeValueAsString(USER_LOGIN_ADMIN))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        LoginResponse loginResponse = objectMapper.readValue(jsonResponse, LoginResponse.class);
        adminJwtToken = loginResponse.getToken();
    }


}
