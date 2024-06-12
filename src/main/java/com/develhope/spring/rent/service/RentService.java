package com.develhope.spring.rent.service;

import com.develhope.spring.rent.repository.RentRepository;
import com.develhope.spring.user.repository.UserRepository;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
}
