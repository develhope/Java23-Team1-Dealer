package com.develhope.spring;

import com.develhope.spring.exception.customException.UserWithoutPrivilegeException;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.service.NecessaryAuthority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class NecessaryAuthorityTest {

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testAdminAndBuyerAuthoritiesGranted() {
        List<UserKind> authorities = new ArrayList<>();
        authorities.add(UserKind.ADMIN);
        authorities.add(UserKind.BUYER);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getAuthorities().stream()
                .map(grantedAuthority -> UserKind.valueOf(grantedAuthority.getAuthority()))
                .toList()).thenReturn(authorities);

     assertDoesNotThrow(() -> NecessaryAuthority.of(UserKind.ADMIN, UserKind.BUYER).grant());
    }

    @Test
    public void testAdminAuthorityGrantedButSellerRequired() {
        List<UserKind> authorities = new ArrayList<>();
        authorities.add(UserKind.BUYER);
        authorities.add(UserKind.ADMIN);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getAuthorities().stream().map(grantedAuthority -> UserKind.valueOf(grantedAuthority.getAuthority()))
                .toList()).thenReturn(authorities);


        UserWithoutPrivilegeException exception = assertThrows(
                UserWithoutPrivilegeException.class,
                () -> NecessaryAuthority.of(UserKind.SELLER).grant());
    }

    @Test
    public void testNoValidAuthentication() {
        when(securityContext.getAuthentication()).thenReturn(null);
        AccessDeniedException exception = assertThrows(
                AccessDeniedException.class,
                () -> NecessaryAuthority.of(UserKind.SELLER).grant());
        assertEquals("No valid authentication", exception.getMessage());
    }
}
