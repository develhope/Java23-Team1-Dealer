package com.develhope.spring.rent.service;

import com.develhope.spring.rent.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;
}
