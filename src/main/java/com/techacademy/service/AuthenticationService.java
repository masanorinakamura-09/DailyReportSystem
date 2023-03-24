package com.techacademy.service;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Authentication;
import com.techacademy.repository.AuthenticationRepository;

@Service
public class AuthenticationService {
    private final AuthenticationRepository repository;

    public AuthenticationService(AuthenticationRepository authentication) {
        this.repository = authentication;
    }

    @Transactional
    public void deleteAuthentication(String code) {
        repository.deleteById(code);
    }



}
