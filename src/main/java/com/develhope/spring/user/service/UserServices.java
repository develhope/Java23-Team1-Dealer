package com.develhope.spring.user.service;

import com.develhope.spring.user.entity.User;
import com.develhope.spring.user.exception.UserNotFoundException;
import com.develhope.spring.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public User createProfile(User user) {
        return userRepository.save(user);
    }

    public void deleteProfile(Long id) {
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
}
