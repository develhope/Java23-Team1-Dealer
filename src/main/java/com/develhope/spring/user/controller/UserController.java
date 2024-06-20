package com.develhope.spring.user.controller;

import com.develhope.spring.user.dto.LoginDto;
import com.develhope.spring.user.dto.RegistrationDto;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.user.entity.LoginResponse;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.service.JwtService;
import com.develhope.spring.user.service.NecessaryAuthority;
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
    public ResponseEntity<User> getUsers() {
        return ResponseEntity.ok().body(userService.loggedInUser());
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUsers() {
        NecessaryAuthority.of(
                        UserKind.ADMIN,
                        UserKind.SELLER)
                .grant();

        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        NecessaryAuthority.of(
                        UserKind.ADMIN,
                        UserKind.SELLER)
                .grant();
        if (userService.findByUsername(username) != null)
            return ResponseEntity.ok(userService.findByUsername(username));
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody RegistrationDto registrationDto) {
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

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam Long id) {
        NecessaryAuthority.of(
                        UserKind.ADMIN,
                        UserKind.SELLER)
                .grant();
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestParam Long id, @RequestBody User userDetails) {
        NecessaryAuthority.of(
                        UserKind.ADMIN,
                        UserKind.SELLER)
                .grant();
        User updatedUser = userService.updateProfile(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

}
