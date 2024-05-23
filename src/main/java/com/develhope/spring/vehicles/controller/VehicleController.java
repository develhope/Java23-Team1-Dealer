package com.develhope.spring.vehicles.controller;

import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.service.VehicleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Vehicles")
public class VehicleController {
    @Autowired
    private VehicleServices vehicleServices;

    @PostMapping
    public Vehicle addVehicle(@RequestBody Vehicle vehicle){
        return vehicleServices.insertVehicle(vehicle);
    }
}
