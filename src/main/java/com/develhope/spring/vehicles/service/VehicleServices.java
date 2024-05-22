package com.develhope.spring.vehicles.service;

import com.develhope.spring.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServices {
    @Autowired
    private VehicleRepository vehicleRepository;
}
