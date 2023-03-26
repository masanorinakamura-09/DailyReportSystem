package com.techacademy.service;



import org.springframework.stereotype.Service;

import com.techacademy.repository.AuthenticationRepository;

@Service
public class AuthenticationService {
    private final AuthenticationRepository repository;

    public AuthenticationService(AuthenticationRepository authentication) {
        this.repository = authentication;
    }


}
