package com.develhope.spring.rent.service;

import com.develhope.spring.rent.repository.RentRepository;
import com.develhope.spring.vehicles.entity.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentServices {
    @Autowired
    private RentRepository rentRepository;
}