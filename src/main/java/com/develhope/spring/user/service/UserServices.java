package com.develhope.spring.user.service;

import com.develhope.spring.user.entity.User;
import com.develhope.spring.user.repository.UserRepository;
import com.develhope.spring.vehicles.entity.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;
}
