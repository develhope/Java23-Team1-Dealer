package com.develhope.spring.vehicles.controller;

import com.develhope.spring.vehicles.service.VehicleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleServices vehicleServices;


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleServices.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }


}
