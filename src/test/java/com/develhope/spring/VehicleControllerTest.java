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
@DirtiesContext
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

    private final static Vehicle DEFAULT_VEHICLE1 = new Vehicle(
            0L, VehicleKind.CAR, "Toyota", "Corolla",
            1600, "Blue", 120, Gearbox.MANUAL,
            2020, FuelType.GASOLINE, 15000.0,
            false, 0.0, "Air Conditioning",
            true, VehicleState.RENTABLE, false, false);
    private final static Vehicle DEFAULT_VEHICLE2 = new Vehicle(
            0L, VehicleKind.CAR, "Toyota", "Corolla",
            1600, "Blue", 120, Gearbox.MANUAL,
            2020, FuelType.GASOLINE, 15000.0,
            false, 0.0, "Air Conditioning",
            true, VehicleState.PURCHASABLE, true, false);

    private final static Vehicle DEFAULT_VEHICLE3 = new Vehicle(
            0L, VehicleKind.SCOOTER, "Yamaha", "Fuciu",
            1600, "Blue", 120, Gearbox.MANUAL,
            2020, FuelType.GASOLINE, 17000.0,
            false, 0.0, "Air Conditioning",
            true, VehicleState.PURCHASABLE, true, false);


    @BeforeEach
    public void setup() throws Exception {
        Vehicle vehicle = new Vehicle(
                0L, VehicleKind.CAR, "Toyota", "Corolla",
                1600, "Blue", 120, Gearbox.MANUAL,
                2020, FuelType.GASOLINE, 15000.0,
                false, 0.0, "Air Conditioning",
                true, VehicleState.RENTABLE, false, false
        );
        vehicleRepository.save(vehicle);

        RegistrationDto registrationDto = new RegistrationDto(
                "Gianni", "rossi", "3456453",
                "jk@gh.it", "gianni", "gianni", UserKind.ADMIN
        );
        userService.create(registrationDto);

        LoginDto loginDto = new LoginDto("gianni", "gianni");
        MvcResult result = mockMvc.perform(post("/user/login")
                        .content(objectMapper.writeValueAsString(loginDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        LoginResponse loginResponse = objectMapper.readValue(jsonResponse, LoginResponse.class);
        adminJwtToken = loginResponse.getToken();
    }


    @Test
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

                .andExpect(status().isFound())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<Vehicle> vehicles = objectMapper.readValue(jsonResponse, List.class);
        assertEquals(1, vehicles.size());
    }

    @Test
    void testCreateVehicle_withValidVehicle() throws Exception {
        MvcResult post = mockMvc.perform(post("/vehicle")
                        .header("Authorization", "Bearer " + adminJwtToken)
                        .content("""
                                {
                                "vehicleKind":"CAR",
                                "brand":"Toyota",
                                "model":"Corolla",
                                "engineSize":300,
                                "color":"blu",
                                "power":150,
                                "gearbox":"MANUAL",
                                "registrationYear":2012,
                                "fuelType":"GASOLINE",
                                "price":160,
                                "isDiscounted":false,
                                "discount":0,
                                "accessories":"none",
                                "isNew":true,
                                "vehicleState":"PURCHASABLE",
                                "purchased":false,
                                "rented":false
                                }
                                """
                        )
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andReturn();
    }

    @Test
    void testfindMostExpensiveSoldedVehicle_withValidVehicle() throws Exception {
        vehicleRepository.save(DEFAULT_VEHICLE2);
        vehicleRepository.save(DEFAULT_VEHICLE3);

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
