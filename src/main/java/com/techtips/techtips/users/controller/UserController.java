package com.techtips.techtips.users.controller;

import com.techtips.techtips.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping("/register")
//    public ResponseEntity<User> createNewUser(RegisterRequest request) {
//        userService
//    }


}
