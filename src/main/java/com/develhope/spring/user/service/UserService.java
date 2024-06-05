package com.develhope.spring.user.service;

import com.develhope.spring.user.entity.User;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.exception.UserNotFoundException;
import com.develhope.spring.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
  
    /*
    * Verifica se il tipo di utente fornito corrisponde al tipo di utente richiesto.
    *
    * @param user: l'utente di cui verificare il tipo
    * @param userKind il tipo di utente richiesto da verificare
    * @return {@code true} se il tipo di utente dell'utente fornito corrisponde al tipo di utente richiesto,
    * {@code false} altrimenti
    */

    public boolean checkAdminPrivilege(User user) {
        return user.getUserKind().equals(UserKind.ADMIN);
    }
    
    public boolean checkSellerPrivilege(User user) {
        return user.getUserKind().equals(UserKind.SELLER);
    }
    
    public boolean checkBuyerPrivilege(User user) {
        return user.getUserKind().equals(UserKind.BUYER);
    }
  
    public boolean checkPermission(User user, UserKind userKind) {
        return user.getUserKind().equals(userKind);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

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