package com.develhope.spring.vehicles.controller;

import com.develhope.spring.exception.customException.VehicleNotFoundException;
import com.develhope.spring.vehicles.dto.MostOrderedVehicleDto;
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
                .status(HttpStatus.FOUND)
                .body(vehicleServiceFilter.getFilteredVehicles());
    }

    @GetMapping("/mostOrderedModel")
    public ResponseEntity<MostOrderedVehicleDto> getMostOrderedVehicleModel() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vehicleService.findMostOrderedVehicleModel());

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicleById(id);
        return ResponseEntity.notFound().build();
    }
}
