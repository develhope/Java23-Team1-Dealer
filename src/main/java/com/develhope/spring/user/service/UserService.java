package com.develhope.spring.user.service;

import com.develhope.spring.user.dto.LoginDto;
import com.develhope.spring.user.dto.RegistrationDto;
import com.develhope.spring.user.entity.Account;
import com.develhope.spring.exception.UserNotFoundException;
import com.develhope.spring.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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



    public List<Account> findAll() {
        return userRepository.findAll();
    }

    public Account create(RegistrationDto registrationDto) {
        Account account = new Account();
        account.setUsername(registrationDto.getUsername());
        account.setName(registrationDto.getName());
        account.setSurname(registrationDto.getSurname());
        account.setMobile(registrationDto.getMobile());
        account.setEmail(registrationDto.getEmail());
        account.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        account.setUserKind(registrationDto.getUserKind());
        return userRepository.save(account);
    }

    public void deleteProfile(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

    public Account updateProfile(Long id, Account accountDetails) {
        Optional<Account> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            Account account = userOptional.get();
            if (accountDetails.getName() != null && !accountDetails.getName().isEmpty()) {
                account.setName(accountDetails.getName());
            }
            if (accountDetails.getSurname() != null && !accountDetails.getSurname().isEmpty()) {
                account.setSurname(accountDetails.getSurname());
            }
            if (accountDetails.getMobile() != null) {
                account.setMobile(accountDetails.getMobile());
            }
            if (accountDetails.getEmail() != null && !accountDetails.getEmail().isEmpty()) {
                account.setEmail(accountDetails.getEmail());
            }
            if (accountDetails.getPassword() != null && !accountDetails.getPassword().isEmpty()) {
                account.setPassword(accountDetails.getPassword());
            }
            if (accountDetails.getUserKind() != null) {
                account.setUserKind(accountDetails.getUserKind());
            }
            return userRepository.save(account);
        } else {
            throw new UserNotFoundException("User not found with id " + id);
        }
    }

    public Account authenticate(LoginDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsernameOrEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsernameOrEmail(input.getUsernameOrEmail(),input.getUsernameOrEmail())
                .orElseThrow();
    }
}