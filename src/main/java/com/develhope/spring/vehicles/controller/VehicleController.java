package com.develhope.spring.vehicles.controller;

import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleState;
import com.develhope.spring.vehicles.service.VehicleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/vehicle")
public class VehicleController {
    @Autowired
    private VehicleServices vehicleServices;
    @GetMapping
    public ResponseEntity<List<Vehicle>> findByVehicleState(@RequestParam VehicleState vehicleState){
        List<Vehicle> results = vehicleServices.findByVehicleState(vehicleState);
        return ResponseEntity.status(HttpStatus.FOUND).body(results) ;
    }
}
