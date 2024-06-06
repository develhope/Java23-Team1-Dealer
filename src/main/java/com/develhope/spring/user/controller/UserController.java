package com.develhope.spring.user.controller;

import com.develhope.spring.user.dto.LoginDto;
import com.develhope.spring.user.dto.RegistrationDto;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.user.entity.LoginResponse;
import com.develhope.spring.user.service.JwtService;
import com.develhope.spring.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<User> createProfile(@RequestBody RegistrationDto registrationDto) {
        User createdUser = userService.create(registrationDto);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginDto loginUserDto) {
        User authenticatedUser = userService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        userService.deleteProfile(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateProfile(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

}
