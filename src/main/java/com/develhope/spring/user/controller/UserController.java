package com.develhope.spring.user.controller;

import com.develhope.spring.user.entity.User;
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
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<User> createProfile(@RequestBody User user) {
        User createdUser = userService.createProfile(user);
        return ResponseEntity.ok(createdUser);
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
