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
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle vehicleAdded = vehicleService.createVehicle(vehicle);
        return ResponseEntity.ok(vehicleAdded);
    }

    @GetMapping(path = "/rentable")
    public ResponseEntity<List<Vehicle>> findRentable() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findByVehicleState(VehicleState.RENTABLE)
                );
    }

    @GetMapping(path = "/purchasable")
    public ResponseEntity<List<Vehicle>> findPurchasable() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findByVehicleState(VehicleState.PURCHASABLE)
                );
    }

    @GetMapping(path = "/not_available")
    public ResponseEntity<List<Vehicle>> findNotAvailable() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findByVehicleState(VehicleState.NOT_AVAILABLE)
                );
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findAll()
                );
    }
}
