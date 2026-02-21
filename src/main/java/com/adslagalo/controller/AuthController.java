package com.adslagalo.controller;

import com.adslagalo.entity.User;
import com.adslagalo.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.adslagalo.dto.RegisterResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody User user) {

        User savedUser = userService.registerUser(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }
}
