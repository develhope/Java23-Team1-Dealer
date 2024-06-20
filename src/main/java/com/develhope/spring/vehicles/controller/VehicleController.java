package com.develhope.spring.vehicles.controller;

import com.develhope.spring.vehicles.entity.*;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import com.develhope.spring.vehicles.service.VehicleService;
import com.develhope.spring.vehicles.service.VehicleServiceFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle vehicleAdded = vehicleService.createVehicle(vehicle);
        return ResponseEntity.ok(vehicleAdded);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Vehicle>> getFilteredVehicles(
            @RequestBody VehicleServiceFilter vehicleServiceFilter
    ) {
        vehicleServiceFilter.setVehicleRepository(vehicleRepository);
        return ResponseEntity
                .ok()
                .body(vehicleServiceFilter.getFilteredVehicles());
    }

    @GetMapping("/mostExpensiveSoldedVehicle")
    public ResponseEntity<Vehicle> findMostExpensiveSoldedVehicle (@RequestBody VehicleServiceFilter vehicleServiceFilter) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(vehicleService.findMostExpensiveSoldedVehicle(vehicleServiceFilter));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicleById(id);
        return ResponseEntity.notFound().build();
    }
}
