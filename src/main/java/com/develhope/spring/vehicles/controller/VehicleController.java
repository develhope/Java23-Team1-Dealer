package com.develhope.spring.vehicles.controller;

import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleServices;

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle){
        Vehicle vehicleAdded = vehicleServices.insertVehicle(vehicle);
        return ResponseEntity.ok(vehicleAdded);
    }
}
