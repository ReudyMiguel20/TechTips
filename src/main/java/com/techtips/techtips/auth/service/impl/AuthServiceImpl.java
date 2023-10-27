package com.techtips.techtips.auth.service.impl;

import com.techtips.techtips.auth.dto.AuthenticationRequest;
import com.techtips.techtips.auth.dto.AuthenticationToken;
import com.techtips.techtips.auth.service.AuthService;
import com.techtips.techtips.common.jwt.JwtService;
import com.techtips.techtips.users.model.dto.RegisterRequest;
import com.techtips.techtips.users.model.entity.User;
import com.techtips.techtips.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationToken register(RegisterRequest request) {
        User newUser = userService.registerNewUserAndAssignRole(request);

        String jwtToken = jwtService.generateToken(newUser);

        return AuthenticationToken.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationToken authenticateUser(AuthenticationRequest authRequest) {
        User user = userService.findByEmail(authRequest.getEmail());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(), authRequest.getPassword())
        );

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationToken.builder()
                .token(jwtToken)
                .build();
    }
}
