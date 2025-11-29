package com.umadecruz.app.controller;

import com.umadecruz.app.dto.LoginRequest;
import com.umadecruz.app.dto.RegisterDto;
import com.umadecruz.app.model.AppUser;
import com.umadecruz.app.repository.AppUserRepository;
import com.umadecruz.app.service.AppUserService;
import com.umadecruz.app.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private JwtService jwt;

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest dto) {
        var u = appUserService.buscarPorEmail(dto.getEmail()).orElse(null);
        if (u==null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");
        }
        if (!encoder.matches(dto.getPassword(), u.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");
        }
        String token = jwt.createToken(u.getEmail(), u.getRole());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterDto dto) {
        if (appUserService.buscarPorEmail(dto.getEmail()).isPresent()){
            return ResponseEntity.status(409).body("exists");
        }

        appUserService.salvar(AppUser.builder()
                        .email(dto.getEmail())
                        .name(dto.getName())
                        .role("ADMIN")
                        .passwordHash(encoder.encode(dto.getPassword()))
                        .build());

        return ResponseEntity.ok("created");
    }
}
