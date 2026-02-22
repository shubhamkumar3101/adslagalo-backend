package com.adslagalo.controller;

import com.adslagalo.entity.User;
import com.adslagalo.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.adslagalo.dto.RegisterResponse;
import com.adslagalo.dto.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.adslagalo.security.JwtService;
import com.adslagalo.dto.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtService.generateToken(authentication.getName());

        return AuthResponse.builder()
                .token(token)
                .build();
    }
    @GetMapping("/test")
    public String test() {
        return "Protected endpoint accessed successfully!";
    }
    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Admin access granted!";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "User access granted!";
    }

}
