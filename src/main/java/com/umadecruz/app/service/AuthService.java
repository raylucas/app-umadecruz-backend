package com.umadecruz.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public String login(String email, String password) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(email, password);
        authManager.authenticate(authToken);
        return jwtService.generateToken(email);
    }
}

