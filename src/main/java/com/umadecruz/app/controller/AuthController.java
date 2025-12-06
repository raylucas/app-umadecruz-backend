package com.umadecruz.app.controller;

import com.umadecruz.app.dto.LoginRequestDto;
import com.umadecruz.app.dto.LoginResponseDto;
import com.umadecruz.app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        String token = authService.login(dto.getEmail(), dto.getSenha());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}

