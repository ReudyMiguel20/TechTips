package com.techtips.techtips.users.controller;

import com.techtips.techtips.users.model.dto.RegisterRequest;
import com.techtips.techtips.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping("/register")
//    public ResponseEntity<User> createNewUser(RegisterRequest request) {
//        userService
//    }

    @PostMapping("/register")
    public ResponseEntity<RegisterRequest> testUser(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(registerRequest);
    }

    @GetMapping("/test")
    public String createNewUser(@RequestBody RegisterRequest registerRequest) {
        return registerRequest.getEmail();
    }


}
