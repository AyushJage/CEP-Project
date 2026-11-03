package com.dbit.rwh.controller;

import com.dbit.rwh.dto.*;
import com.dbit.rwh.entity.Role;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.UserRepository;
import com.dbit.rwh.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        if (userRepo.existsByPhone(req.getPhone())) {
            throw new IllegalArgumentException("A user with phone number " + req.getPhone() + " already exists.");
        }

        User user = User.builder()
                .name(req.getName())
                .phone(req.getPhone())
                .passwordHash(passwordEncoder.encode(req.getPassword()))
                .role(Role.FARMER)
                .village(req.getVillage())
                .taluka(req.getTaluka())
                .district(req.getDistrict())
                .language(req.getLanguage() != null ? req.getLanguage() : "mr")
                .build();
        userRepo.save(user);

        String token = jwtUtil.generateToken(user.getPhone(), user.getRole().name());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                AuthResponse.builder()
                        .token(token)
                        .user(toUserDto(user))
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getPhone(), req.getPassword()));

        User user = userRepo.findByPhone(req.getPhone())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String token = jwtUtil.generateToken(user.getPhone(), user.getRole().name());
        return ResponseEntity.ok(
                AuthResponse.builder()
                        .token(token)
                        .user(toUserDto(user))
                        .build()
        );
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(@AuthenticationPrincipal UserDetails principal) {
        User user = userRepo.findByPhone(principal.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return ResponseEntity.ok(toUserDto(user));
    }

    private UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .role(user.getRole())
                .language(user.getLanguage())
                .village(user.getVillage())
                .taluka(user.getTaluka())
                .district(user.getDistrict())
                .build();
    }

    public AuthController(UserRepository userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

}
