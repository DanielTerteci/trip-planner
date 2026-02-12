package com.trip.planner.citybreak.controller;

import com.trip.planner.citybreak.dto.LoginDto;
import com.trip.planner.citybreak.dto.RegisterDto;
import com.trip.planner.citybreak.dto.UserDto;
import com.trip.planner.citybreak.models.User;
import com.trip.planner.citybreak.repository.UserRepository;
import com.trip.planner.citybreak.security.AuthResponse;
import com.trip.planner.citybreak.security.JwtTokenProvider;
import com.trip.planner.citybreak.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {
        try {
            UserDto user = userService.registerUser(registerDto);

            // Automatically log in after registration
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            String token = jwtTokenProvider.generateToken(userDetails);

            User userEntity = userRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            AuthResponse response = AuthResponse.builder()
                    .token(token)
                    .type("Bearer")
                    .userId(user.getId())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .role(userEntity.getRole().name())
                    .subscriptionPlan(userEntity.getSubscriptionPlan().name())
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );

            // Generate JWT token
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());
            String token = jwtTokenProvider.generateToken(userDetails);

            // Get user details
            User user = userRepository.findByEmail(loginDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            AuthResponse response = AuthResponse.builder()
                    .token(token)
                    .type("Bearer")
                    .userId(user.getId())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .role(user.getRole().name())
                    .subscriptionPlan(user.getSubscriptionPlan().name())
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
    }
}