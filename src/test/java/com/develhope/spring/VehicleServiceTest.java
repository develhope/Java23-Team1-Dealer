package com.develhope.spring;

import com.develhope.spring.exception.VehicleNotFoundException;
import com.develhope.spring.vehicles.entity.FuelType;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleKind;
import com.develhope.spring.vehicles.entity.VehicleState;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import com.develhope.spring.vehicles.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class VehicleServiceTest {
    @MockBean
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleService vehicleService;

    private static final Vehicle DEFAULT_VEHICLE = new Vehicle(
            1, VehicleKind.MOTORCYCLE, "HONDA", "yuhuu", 800,
            "black", 2, "de", 1, FuelType.GASOLINE,
            2022, 33, "gb", true, VehicleState.PURCHASABLE);


    @Test
    void deleteVehicle_withValidVehicle() {
        long vehicleID = 1;
        when(vehicleRepository.existsById(vehicleID)).thenReturn(true);
        vehicleService.deleteVehicle(vehicleID);
        verify(vehicleRepository, times(1)).deleteById(vehicleID);

    }

    @Test
    void deleteVehicle_withInvalidVehicle() {
        long vehicleID = 0;
        when(vehicleRepository.existsById(vehicleID)).thenReturn(false);
        assertThrows(VehicleNotFoundException.class, () -> vehicleService.deleteVehicle(vehicleID));

    }

}
