package com.develhope.spring.user.service;

import com.develhope.spring.user.dto.LoginDto;
import com.develhope.spring.user.dto.RegistrationDto;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.exception.customException.UserNotFoundException;
import com.develhope.spring.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;



    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) throw new UserNotFoundException("No users where register");
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("User not found with username " + username));
    }

    public User create(RegistrationDto registrationDto) {
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new BadCredentialsException("Username is already in use");
        };
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new BadCredentialsException("Email is already in use");
        }
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setName(registrationDto.getName());
        user.setSurname(registrationDto.getSurname());
        user.setMobile(registrationDto.getMobile());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setUserKind(registrationDto.getUserKind());
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

    public User updateProfile(Long id, User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (userDetails.getName() != null && !userDetails.getName().isEmpty()) {
                user.setName(userDetails.getName());
            }
            if (userDetails.getSurname() != null && !userDetails.getSurname().isEmpty()) {
                user.setSurname(userDetails.getSurname());
            }
            if (userDetails.getMobile() != null) {
                user.setMobile(userDetails.getMobile());
            }
            if (userDetails.getEmail() != null && !userDetails.getEmail().isEmpty()) {
                user.setEmail(userDetails.getEmail());
            }
            if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                user.setPassword(userDetails.getPassword());
            }
            if (userDetails.getUserKind() != null) {
                user.setUserKind(userDetails.getUserKind());
            }
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found with id " + id);
        }
    }

    public User authenticate(LoginDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsernameOrEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsernameOrEmail(input.getUsernameOrEmail(),input.getUsernameOrEmail())
                .orElseThrow();
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}