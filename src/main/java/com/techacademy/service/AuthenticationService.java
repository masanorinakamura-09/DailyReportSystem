package com.techacademy.service;

import org.springframework.stereotype.Service;

import com.techacademy.repository.AuthenticationRepository;

@Service
public class AuthenticationService {
    private final AuthenticationRepository authentication;

    public AuthenticationService(AuthenticationRepository authentication) {
        this.authentication = authentication;
    }
}
