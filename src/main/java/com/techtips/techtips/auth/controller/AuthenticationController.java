package com.techtips.techtips.auth.controller;

import com.techtips.techtips.auth.dto.AuthenticationRequest;
import com.techtips.techtips.auth.dto.AuthenticationToken;
import com.techtips.techtips.auth.service.AuthService;
import com.techtips.techtips.users.model.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationToken> registerNewUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping
    public ResponseEntity<AuthenticationToken> authenticateExistingUser(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticateUser(request));
    }
}
