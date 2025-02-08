package com.example.authservice.controller;

import com.example.authservice.model.AuthRequest;
import com.example.authservice.model.AuthResponse;
import com.example.authservice.model.User;
import com.example.authservice.service.JwtUtil;
import com.example.authservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        User user = userService.findByUsername(request.getUsername());

        if (user != null && user.getPassword().equals(request.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            return new AuthResponse(token);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
