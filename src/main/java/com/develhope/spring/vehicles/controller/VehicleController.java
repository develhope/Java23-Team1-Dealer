package com.develhope.spring.vehicles.controller;

import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleState;
import com.develhope.spring.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        Vehicle vehicleAdded = vehicleService.insertVehicle(vehicle);
        return ResponseEntity.ok(vehicleAdded);
    }

    @PostMapping("/shop")
    public ResponseEntity<String> shopVehicle(@RequestBody Vehicle vehicle, @RequestParam VehicleState isPurchasable) {
        try {
            boolean isPurchased = vehicleService.shopVehicle(vehicle, isPurchasable);
            if (isPurchased) {
                return ResponseEntity.ok("Vehicle has been successfully purchased!");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle purchase failed, please call our agency for more information.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
