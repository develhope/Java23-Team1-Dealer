package com.develhope.spring.user.service;

import com.develhope.spring.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    @Autowired
    private UserRepository userRepository;
}
