package com.develhope.spring;

import com.develhope.spring.purchase_order.dto.PurchaseOrderCreationDTO;
import com.develhope.spring.purchase_order.dto.PurchaseOrderResponseDTO;
import com.develhope.spring.purchase_order.entity.PurchaseOrderStatus;
import com.develhope.spring.purchase_order.service.PurchaseOrderService;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@Profile("test")
@AutoConfigureMockMvc
public class PurchaseOrderControllerTest {
    @Autowired
    private PurchaseOrderService purchaseService;
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

    private static final PurchaseOrderCreationDTO ORDER_CREATION_DTO = new PurchaseOrderCreationDTO(
            1, 100, false, PurchaseOrderStatus.ORDERED, 1,1
    );


    @BeforeEach
    void setUp() throws Exception {
        userService.create(registrationDto);
        vehicleService.createVehicle(vehicle);

        User response = userService.authenticate(
                new LoginDto(registrationDto.getUsername(), registrationDto.getPassword()));
        assertNotNull(response);
        jwtToken = jwtService.generateToken(response);

    }

    @Test
    @DirtiesContext
    void createOrder() throws Exception {
       MvcResult result = mockMvc.perform(post("/purchase_order")
                .header("Authorization", "Bearer " + jwtToken)
                .content(objectMapper.writeValueAsString(ORDER_CREATION_DTO))
                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        PurchaseOrderResponseDTO orderReturned = objectMapper.readValue(responseBody, PurchaseOrderResponseDTO.class);
        assertNotNull(orderReturned);
    }
    @Test
    @DirtiesContext
    public void testFilterOrders() throws Exception {
        purchaseService.createOrder(ORDER_CREATION_DTO);
        MvcResult result = mockMvc.perform(get("/purchase_order")
                        .header("Authorization", "Bearer " + jwtToken)
                        .content("""
                                {
                                "buyerId": 1,
                                "paid": false
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
}
