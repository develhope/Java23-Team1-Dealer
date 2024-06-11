package com.develhope.spring;

import com.develhope.spring.user.dto.LoginDto;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.repository.UserRepository;
import com.develhope.spring.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class UserServiceUnitTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    private static final LoginDto LOGIN_DTO = new LoginDto("gianni", "12345");
    private static final User USER = new User(
            1L, "Mara", "rossi", "3456453",
            "mara@gh.it", "mara", "gianni", UserKind.ADMIN
    );

    private final static UsernamePasswordAuthenticationToken DEFAULT_LOGIN_TOKEN =
            new UsernamePasswordAuthenticationToken(
                    LOGIN_DTO.getUsernameOrEmail(),
                    LOGIN_DTO.getPassword()
            );

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticate() {
        when(authenticationManager.authenticate(any()))
                .thenReturn(DEFAULT_LOGIN_TOKEN);
        when(userRepository.findByUsernameOrEmail(
                LOGIN_DTO.getUsernameOrEmail(),
                LOGIN_DTO.getUsernameOrEmail()))
                .thenReturn(Optional.of(USER));

        User returnedUser = userService.authenticate(LOGIN_DTO);
        assertEquals(USER, returnedUser);
    }

}
