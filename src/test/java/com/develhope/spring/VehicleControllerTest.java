package com.develhope.spring;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @BeforeEach
    public void setup() {
        vehicleRepository.deleteAll();
        Vehicle vehicle = new Vehicle(0L, VehicleKind.CAR, "Toyota", "Corolla", 1600, "Blue", 120, Gearbox.MANUAL, 2020, FuelType.GASOLINE, 15000.0, false, 0.0, "Air Conditioning", true, VehicleState.RENTABLE, false, false);
        vehicleRepository.save(vehicle);
    }

    @Test
    public void testGetFilteredVehicles() throws Exception {
        MvcResult result = mockMvc.perform(get("/vehicles/filter")
                        .param("brand", "Toyota")
                        .param("model", "Corolla")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<Vehicle> vehicles = objectMapper.readValue(jsonResponse, List.class);
        assert vehicles.size() == 1;
    }
}
