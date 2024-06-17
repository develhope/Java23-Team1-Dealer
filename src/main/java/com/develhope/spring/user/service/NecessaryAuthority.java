package com.develhope.spring.user.service;

import com.develhope.spring.user.entity.UserKind;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;

/**
 * The {@code NecessaryAuthority} class provides methods to check if the current user has the necessary authority
 * to perform specific actions. It uses Spring Security's {@code Authentication} to verify the user's roles.
 */
public class NecessaryAuthority {

    private final List<UserKind> authorities;

    /**
     * Private constructor to initialize the {@code NecessaryAuthority} with the given authorities.
     *
     * @param authorities Varargs parameter to specify the required user kinds.
     */
    private NecessaryAuthority(UserKind... authorities) {
        this.authorities = Arrays.asList(authorities);
    }

    /**
     * Creates a {@code NecessaryAuthority} instance with the specified authorities.
     *
     * @param authorities Varargs parameter to specify the required user kinds.
     * @return A {@code NecessaryAuthority} instance for the specified roles.
     */
    public static NecessaryAuthority of(UserKind... authorities) {
        return new NecessaryAuthority(authorities);
    }

    /**
     * Checks if the current user has the necessary authorities. If the user does not have the required authority,
     * an {@code AccessDeniedException} is thrown.
     *
     * @throws AccessDeniedException if the current user does not have the required authority.
     */
    public void grant() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AccessDeniedException("No valid authentication");
        }

        List<UserKind> loggedUserAuthorityList = authentication.getAuthorities().stream()
                .map(grantedAuthority -> UserKind.valueOf(grantedAuthority.getAuthority()))
                .toList();

        for (UserKind authority : authorities) {
            if (loggedUserAuthorityList.getFirst().equals(authority)) {
                return;
            }

        }
        throw new AccessDeniedException("Access denied. User " + authentication.getName()
                + " does not have the required authority. \n" +
                "Action permitted by user kind: " + authorities.toString());

    }

    /**
     * Checks if the current user has the necessary authorities. If the user does not have the required authority,
     * {@code false} is returned.
     *
     * @throws AccessDeniedException if a no valid authentication is present.
     */

    public boolean check() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AccessDeniedException("No valid authentication");
        }

        List<UserKind> loggedUserAuthorityList = authentication.getAuthorities().stream()
                .map(grantedAuthority -> UserKind.valueOf(grantedAuthority.getAuthority()))
                .toList();

        for (UserKind authority : authorities) {
            if (loggedUserAuthorityList.getFirst().equals(authority)) {
                return true;
            }

        }
        return false;
    }
}


