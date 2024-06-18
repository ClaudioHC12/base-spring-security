package com.curso.base_spring_security.controller;

import com.curso.base_spring_security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.base_spring_security.dto.AuthenticationRequest;
import com.curso.base_spring_security.dto.AuthenticationResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/dev/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("permitAll")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationRequest authenticationRequest) {
        AuthenticationResponse jwtDto = authenticationService.login(authenticationRequest);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @PreAuthorize("permitAll")
    @GetMapping("/public-access")
    public ResponseEntity<String> publicAccessEndpoint() {
        return new ResponseEntity<>("Public access", HttpStatus.OK);
    }
}
