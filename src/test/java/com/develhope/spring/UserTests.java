package com.develhope.spring;

import com.develhope.spring.exception.customException.UserNotFoundException;
import com.develhope.spring.user.dto.LoginDto;
import com.develhope.spring.user.dto.RegistrationDto;
import com.develhope.spring.user.entity.LoginResponse;
import com.develhope.spring.user.entity.User;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.service.JwtService;
import com.develhope.spring.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    //  Test Service

    @Test
    public void create() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto(
                "Sara", "rossi", "3456453",
                "root@gh.it", "root", "gianni", UserKind.ADMIN
        );
        User userCreated = userService.create(registrationDto);
        assertEquals(registrationDto.getEmail(), userCreated.getEmail());
    }

    @Test
    public void createDuplicateEmail() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto(
                "Sara", "rossi", "3456453",
                "sara@gh.it", "sara", "gianni", UserKind.ADMIN
        );
        userService.create(registrationDto);
        registrationDto.setUsername("sara3");
        assertThrows(
                BadCredentialsException.class,
                () -> userService.create(registrationDto)
        );
    }

    @Test
    public void createDuplicatedUsername() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto(
                "Luke", "rossi", "3456453",
                "ljh@gh.it", "jhjkhkjhk", "gianni", UserKind.ADMIN
        );
        userService.create(registrationDto);
        registrationDto.setEmail("hkjhskj@sdsa.it");
        assertThrows(
                BadCredentialsException.class,
                () -> userService.create(registrationDto));
    }

    @Test
    public void findAll() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto(
                "Mara", "rossi", "3456453",
                "mara@gh.it", "mara", "gianni", UserKind.ADMIN
        );
        userService.create(registrationDto);
        List<User> users = userService.findAll();
        assertNotNull(users);
    }


    @Test
    public void existById() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto(
                "Luca", "rossi", "3456453",
                "luca@gh.it", "luca", "gianni", UserKind.ADMIN
        );
        User returnedUser = userService.create(registrationDto);
        boolean response = userService.existsById(returnedUser.getId());
        assertTrue(response);
    }

    @Test
    public void notExistById() throws Exception {
        boolean response = userService.existsById(0L);
        assertFalse(response);
    }

    @Test
    public void findByUsername() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto(
                "jkl", "rossi", "3456453",
                "gianni@gh.it", "jkl", "gianni", UserKind.ADMIN
        );
        User createdUser = userService.create(registrationDto);
        User response = userService.findByUsername(createdUser.getUsername());
        assertNotNull(response);
    }

    @Test
    public void findByUsernameFail() throws Exception {
        assertThrowsExactly(
                UsernameNotFoundException.class,
                () -> userService.findByUsername("")
        );
    }

    @Test
    public void Authenticate() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto(
                "Viola", "rossi", "3456453",
                "viola@gh.it", "viola", "gianni", UserKind.ADMIN
        );
        userService.create(registrationDto);
        User response = userService.authenticate(
                new LoginDto("viola", "gianni"));
        assertNotNull(response);
    }

    @Test
    public void AuthenticateFail() throws Exception {
        assertThrowsExactly(
                BadCredentialsException.class,
                () -> userService.authenticate(
                        new LoginDto("klj", "hj")
                )
        );
    }

    @Test
    public void DeleteByID() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto(
                "Mattia", "rossi", "3456453",
                "mattia@gh.it", "mattia", "gianni", UserKind.ADMIN
        );
        User createdUser = userService.create(registrationDto);
        User response = userService.findByUsername(createdUser.getUsername());
        assertNotNull(response);
    }

    @Test
    public void deleteByIdFail() throws Exception {
        assertThrowsExactly(
                UserNotFoundException.class,
                () -> userService.deleteById(0L)
        );
    }

    //  Test Controller endPoint
    @Test
    public void register() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto(
                "Filippo", "rossi", "3456453",
                "fi@gh.it", "fi", "gianni", UserKind.ADMIN
        );

        mockMvc.perform(post("/user")
                        .content(objectMapper.writeValueAsString(registrationDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void login() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto(
                "fifi", "rossi", "3456453",
                "fifi@gh.it", "fifi", "gianni", UserKind.ADMIN
        );
        User createdUser = userService.create(registrationDto);

        LoginDto loginDto = new LoginDto("fifi", "gianni");
        MvcResult result = mockMvc.perform(post("/user/login")
                        .content(objectMapper.writeValueAsString(loginDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        LoginResponse loginResponse = objectMapper.readValue(jsonResponse, LoginResponse.class);
        assertNotNull(loginResponse.getToken());
    }

    @Test
    public void deleteUser() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto(
                "gh", "rossi", "3456453",
                "a@gh.it", "gh", "gianni", UserKind.ADMIN
        );
        User returnedUser = userService.create(registrationDto);

        String jwtToken = jwtService.generateToken(returnedUser);

        mockMvc.perform(delete("/user?id=" + returnedUser.getId())
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk());
    }


}
