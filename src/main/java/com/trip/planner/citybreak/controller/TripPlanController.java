package com.trip.planner.citybreak.controller;

import com.trip.planner.citybreak.dto.TripPlanDto;
import com.trip.planner.citybreak.security.SecurityUtils;
import com.trip.planner.citybreak.service.TripPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trip-plans")
@RequiredArgsConstructor
public class TripPlanController {

    private final TripPlanService tripPlanService;
    private final SecurityUtils securityUtils;

    @PostMapping
    public ResponseEntity<?> createTripPlan(
            @RequestBody TripPlanDto tripPlanDto,
            @RequestHeader("Authorization") String authHeader) {
        try {
            Long userId = securityUtils.extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            // Force userId from token (security measure)
            tripPlanDto.setUserId(userId);

            TripPlanDto created = tripPlanService.createTripPlan(tripPlanDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTripPlans() {
        // Only admins can see all trip plans
        if (!securityUtils.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied. Only admins can view all trip plans.");
        }

        List<TripPlanDto> tripPlans = tripPlanService.getAllTripPlans();
        return ResponseEntity.ok(tripPlans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTripPlanById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {
        try {
            TripPlanDto tripPlan = tripPlanService.getTripPlanById(id);
            Long userId = securityUtils.extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            // Security check: User can only view their own trips
            if (!tripPlan.getUserId().equals(userId) && !securityUtils.isAdmin()) {
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

        Long tokenUserId = securityUtils.extractUserIdFromToken(authHeader);

        if (tokenUserId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid token");
        }

        // Security check: User can only view their own trips
        if (!tokenUserId.equals(userId) && !securityUtils.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You can only view your own trip plans. Your userId: " + tokenUserId);
        }

        List<TripPlanDto> tripPlans = tripPlanService.getTripPlansByUserId(userId);
        return ResponseEntity.ok(tripPlans);
    }

    @GetMapping("/my-trips")
    public ResponseEntity<?> getMyTrips(@RequestHeader("Authorization") String authHeader) {
        try {
            Long userId = securityUtils.extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token: userId not found");
            }

            List<TripPlanDto> tripPlans = tripPlanService.getTripPlansByUserId(userId);
            return ResponseEntity.ok(tripPlans);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or expired token");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTripPlan(
            @PathVariable Long id,
            @RequestBody TripPlanDto tripPlanDto,
            @RequestHeader("Authorization") String authHeader) {
        try {
            Long userId = securityUtils.extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            TripPlanDto existing = tripPlanService.getTripPlanById(id);

            if (!existing.getUserId().equals(userId) && !securityUtils.isAdmin()) {
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
            Long userId = securityUtils.extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            TripPlanDto existing = tripPlanService.getTripPlanById(id);

            if (!existing.getUserId().equals(userId) && !securityUtils.isAdmin()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You can only update your own trip plans");
            }

            TripPlanDto updated = tripPlanService.updateTripPlanStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTripPlan(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {
        try {
            Long userId = securityUtils.extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            TripPlanDto existing = tripPlanService.getTripPlanById(id);

            if (!existing.getUserId().equals(userId) && !securityUtils.isAdmin()) {
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