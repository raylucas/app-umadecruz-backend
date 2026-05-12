package com.umadecruz.app.controller;

import com.umadecruz.app.dto.TokenDto;
import com.umadecruz.app.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService service;

    @PostMapping
    public ResponseEntity<Void> registrar(@RequestBody TokenDto dto) {
        service.registrar(dto);
        return ResponseEntity.ok().build();
    }

}