package com.develhope.spring.vehicles.controller;

import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleState;
import com.develhope.spring.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/user/{userId}")
    public ResponseEntity<String> readPurchasedVehiclesByUserId(@PathVariable Long userId) {
        List<Vehicle> vehicles = vehicleService.readPurchasedVehiclesByUserId(userId);
        if (vehicles.isEmpty()) {
            String errorMessage = "This user " + userId + " has not yet purchased vehicles with us.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        return ResponseEntity.ok(vehicles.toString());
    }
}
