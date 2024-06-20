package com.develhope.spring;

import com.develhope.spring.user.dto.LoginDto;
import com.develhope.spring.user.dto.RegistrationDto;
import com.develhope.spring.user.entity.LoginResponse;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.service.UserService;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleKind;
import com.develhope.spring.vehicles.entity.FuelType;
import com.develhope.spring.vehicles.entity.Gearbox;
import com.develhope.spring.vehicles.entity.VehicleState;
import com.develhope.spring.vehicles.repository.VehicleRepository;
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

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserService userService;

    String adminJwtToken;

    private final static RegistrationDto USER_REGISTRATION = new RegistrationDto("Gianni", "Rossi",
            "3456453222", "jk@gh.it",
            "gianni", "gianni", UserKind.ADMIN);
    private final static LoginDto USER_LOGIN = new LoginDto("gianni", "gianni");
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
            true, VehicleState.PURCHASED);
    private final static Vehicle DEFAULT_VEHICLE4 = new Vehicle(
            0L, VehicleKind.VAN, "FIAT", "Doblo",
            1200, "White", 100, Gearbox.MANUAL,
            2015, FuelType.BATTERY, 9500.0,
            false, 0.0, "none",
            true, VehicleState.PURCHASABLE);

    @BeforeEach
    public void setup() throws Exception {
        vehicleRepository.save(DEFAULT_VEHICLE1);
        vehicleRepository.save(DEFAULT_VEHICLE2);
        vehicleRepository.save(DEFAULT_VEHICLE3);
        vehicleRepository.save(DEFAULT_VEHICLE4);
        userService.create(USER_REGISTRATION);

        MvcResult result = mockMvc.perform(post("/user/login")
                        .content(objectMapper.writeValueAsString(USER_LOGIN))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        LoginResponse loginResponse = objectMapper.readValue(jsonResponse, LoginResponse.class);
        adminJwtToken = loginResponse.getToken();
    }

    @Test
    @DirtiesContext
    void testCreateVehicle_withValidVehicle() throws Exception {
        this.mockMvc.perform(post("/vehicle")
                        .header("Authorization", "Bearer " + adminJwtToken)
                        .content(objectMapper.writeValueAsString(DEFAULT_VEHICLE1)
                        )
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andReturn();
    }

    @Test
    @DirtiesContext
    public void testGetFilteredVehicles() throws Exception {
        MvcResult result = mockMvc.perform(get("/vehicle/filter")
                        .header("Authorization", "Bearer " + adminJwtToken)
                        .content("""
                                {
                                "brand":"Toyota",
                                "model":"Corolla"
                                }
                                """
                        )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<Vehicle> vehicles = objectMapper.readValue(jsonResponse, List.class);
        assertEquals(1, vehicles.size());
    }

    @Test
    @DirtiesContext
    void testfindMostExpensiveSoldedVehicle_withValidVehicle() throws Exception {
        this.mockMvc.perform(get("/vehicle/mostExpensiveSoldedVehicle")
                .header("Authorization", "Bearer " + adminJwtToken)
                        .content("""
                                {
                                "isPurchased":true
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(3))
                .andReturn();
    }

}
