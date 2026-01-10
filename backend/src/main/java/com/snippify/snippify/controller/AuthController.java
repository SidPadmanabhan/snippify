package com.snippify.snippify.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.snippify.snippify.model.User;
import com.snippify.snippify.dto.AuthRequest;
import com.snippify.snippify.dto.AuthResponse;
import com.snippify.snippify.repository.UserRepository;
import com.snippify.snippify.security.JwtUtils;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//handle registration

//handle login

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    // constructor
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    // handle register
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        // hash the password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(request.getEmail(), hashedPassword);

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully.");
    }

    // handle login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        
        //find the user by their email
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        //check if user and password exists
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        //if both exist, generate the jwt token
        String token = jwtUtils.generateToken(user.getEmail()); 

        return ResponseEntity.ok(new AuthResponse(token)); 

    }
}
