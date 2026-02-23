package com.trip.planner.citybreak.controller;

import com.trip.planner.citybreak.dto.TripPlanDto;
import com.trip.planner.citybreak.security.JwtTokenProvider;
import com.trip.planner.citybreak.service.TripPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trip-plans")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TripPlanController {

    private final TripPlanService tripPlanService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Helper method to extract userId from JWT token
     */
    private Long extractUserIdFromToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        String token = authHeader.substring(7);
        return jwtTokenProvider.extractUserId(token);
    }

    /**
     * Helper method to check if user is admin
     */
    private boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }

    @PostMapping
    public ResponseEntity<?> createTripPlan(
            @RequestBody TripPlanDto tripPlanDto,
            @RequestHeader("Authorization") String authHeader) {
        try {
            // Extract userId from token
            Long userId = extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            tripPlanDto.setUserId(userId);
            TripPlanDto created = tripPlanService.createTripPlan(tripPlanDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllTripPlans")
    public ResponseEntity<List<TripPlanDto>> getAllTripPlans() {
        List<TripPlanDto> tripPlans = tripPlanService.getAllTripPlans();
        return ResponseEntity.ok(tripPlans);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getTripPlanById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {
        try {
            TripPlanDto tripPlan = tripPlanService.getTripPlanById(id);

            Long userId = extractUserIdFromToken(authHeader);
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }
            System.out.println("Extracted userId from token: " + userId);

            if (!tripPlan.getUserId().equals(userId) && !isAdmin()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You can only view your own trip plans");
            }

            return ResponseEntity.ok(tripPlan);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getTripPlansByUser(
            @PathVariable Long userId,
            @RequestHeader("Authorization") String authHeader) {

        Long tokenUserId = extractUserIdFromToken(authHeader);
        if (tokenUserId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid token");
        }

        if (!tokenUserId.equals(userId) && !isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You can only view your own trip plans. Your userId: " + tokenUserId);
        }

        List<TripPlanDto> tripPlans = tripPlanService.getTripPlansByUserId(userId);
        return ResponseEntity.ok(tripPlans);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTripPlan(
            @PathVariable Long id,
            @RequestBody TripPlanDto tripPlanDto,
            @RequestHeader("Authorization") String authHeader) {
        try {

            Long userId = extractUserIdFromToken(authHeader);
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            TripPlanDto existing = tripPlanService.getTripPlanById(id);

            if (!existing.getUserId().equals(userId) && !isAdmin()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You can only update your own trip plans");
            }

            TripPlanDto updated = tripPlanService.updateTripPlan(id, tripPlanDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateTripPlanStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestHeader("Authorization") String authHeader) {
        try {

            Long userId = extractUserIdFromToken(authHeader);
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            TripPlanDto existing = tripPlanService.getTripPlanById(id);
            if (!existing.getUserId().equals(userId) && !isAdmin()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You can only update your own trip plans");
            }

            TripPlanDto updated = tripPlanService.updateTripPlanStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTripPlan(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {
        try {

            Long userId = extractUserIdFromToken(authHeader);
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            TripPlanDto existing = tripPlanService.getTripPlanById(id);
            if (!existing.getUserId().equals(userId) && !isAdmin()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You can only delete your own trip plans");
            }
            tripPlanService.deleteTripPlan(id);

            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}