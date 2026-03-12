package com.trip.planner.citybreak.controller;

import com.trip.planner.citybreak.dto.UserDto;
import com.trip.planner.citybreak.mapper.UserMapper;
import com.trip.planner.citybreak.models.User;
import com.trip.planner.citybreak.repository.UserRepository;
import com.trip.planner.citybreak.security.SecurityUtils;
import com.trip.planner.citybreak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final SecurityUtils securityUtils;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        // Only admins can view all users
        if (!securityUtils.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied. Only admins can view all users.");
        }

        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {
        try {
            Long userId = securityUtils.extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            // Users can only view their own profile (unless admin)
            if (!userId.equals(id) && !securityUtils.isAdmin()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You can only view your own profile");
            }

            UserDto user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        // Only admins can search users by email
        if (!securityUtils.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied. Only admins can search users by email.");
        }

        try {
            UserDto user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/myProfile")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        try {
            Long userId = securityUtils.extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            UserDto user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody UserDto userDto,
            @RequestHeader("Authorization") String authHeader) {
        try {
            Long userId = securityUtils.extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            // Users can only update their own profile
            if (!userId.equals(id) && !securityUtils.isAdmin()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You can only update your own profile");
            }

            UserDto updated = userService.updateUser(id, userDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/upgrade-pro")
    public ResponseEntity<?> upgradeToPro(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {
        try {
            Long userId = securityUtils.extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            // Users can only upgrade their own account
            if (!userId.equals(id) && !securityUtils.isAdmin()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You can only upgrade your own account");
            }

            UserDto upgraded = userService.upgradeToPro(id);
            return ResponseEntity.ok(upgraded);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        // Only admins can delete users
        if (!securityUtils.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied. Only admins can delete users.");
        }

        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/dark-mode")
    public ResponseEntity<UserDto> updateDarkMode(
            @PathVariable Long id,
            @RequestParam Boolean darkMode) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            user.setDarkMode(darkMode);
            user.setUpdatedAt(LocalDateTime.now());

            User updated = userRepository.save(user);
            return ResponseEntity.ok(UserMapper.toDto(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}