package com.develhope.spring.user.controller;

import com.develhope.spring.user.dto.RegistrationDto;
import com.develhope.spring.user.entity.Account;
import com.develhope.spring.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Account> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createProfile(@RequestBody RegistrationDto registrationDto) {
        Account createdAccount = userService.create(registrationDto);
        return ResponseEntity.ok(createdAccount);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        userService.deleteProfile(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Account> updateProfile(@PathVariable Long id, @RequestBody Account accountDetails) {
        Account updatedAccount = userService.updateProfile(id, accountDetails);
        return ResponseEntity.ok(updatedAccount);
    }

}
