package com.develhope.spring.user.service;

import com.develhope.spring.user.entity.User;
import com.develhope.spring.user.entity.UserKind;
import com.develhope.spring.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    /*
     * Verifica se il tipo di utente fornito corrisponde al tipo di utente richiesto.
     *
     * @param user l'utente di cui verificare il tipo
     * @param userKind il tipo di utente richiesto da verificare
     * @return {@code true} se il tipo di utente dell'utente fornito corrisponde al tipo di utente richiesto,
     *         {@code false} altrimenti
     */
    public boolean checkPermission(User user, UserKind userKind) {
        return user.getUserKind().equals(userKind);
    }
}
