package com.snowlmanlabs.catalivros.application.controller;

import com.snowlmanlabs.catalivros.application.conf.JwtAuthenticationService;
import com.snowlmanlabs.catalivros.application.controller.dto.JwtResponse;
import com.snowlmanlabs.catalivros.application.controller.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtAuthenticationService authenticationService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        String token = authenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
