package com.trip.planner.citybreak.controller;

import com.trip.planner.citybreak.dto.ReviewDto;
import com.trip.planner.citybreak.security.SecurityUtils;
import com.trip.planner.citybreak.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;
    private final SecurityUtils securityUtils;

    @PostMapping
    public ResponseEntity<?> createReview(
            @RequestBody ReviewDto reviewDto,
            @RequestHeader("Authorization") String authHeader) {
        try {
            Long userId = securityUtils.extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            // Force userId from token (security measure)
            reviewDto.setUserId(userId);

            ReviewDto created = reviewService.createReview(reviewDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        // Public endpoint - anyone can view reviews
        List<ReviewDto> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) {
        // Public endpoint
        try {
            ReviewDto review = reviewService.getReviewById(id);
            return ResponseEntity.ok(review);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/destination/{destinationId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByDestination(@PathVariable Long destinationId) {
        // Public endpoint
        List<ReviewDto> reviews = reviewService.getReviewsByDestination(destinationId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getReviewsByUser(
            @PathVariable Long userId,
            @RequestHeader("Authorization") String authHeader) {

        Long tokenUserId = securityUtils.extractUserIdFromToken(authHeader);

        if (tokenUserId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid token");
        }

        // Users can only view their own reviews (unless admin)
        if (!tokenUserId.equals(userId) && !securityUtils.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You can only view your own reviews");
        }

        List<ReviewDto> reviews = reviewService.getReviewsByUser(userId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/my-reviews")
    public ResponseEntity<?> getMyReviews(@RequestHeader("Authorization") String authHeader) {
        try {
            Long userId = securityUtils.extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            List<ReviewDto> reviews = reviewService.getReviewsByUser(userId);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or expired token");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewDto reviewDto,
            @RequestHeader("Authorization") String authHeader) {
        try {
            Long userId = securityUtils.extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            ReviewDto existing = reviewService.getReviewById(id);

            // Users can only update their own reviews
            if (!existing.getUserId().equals(userId) && !securityUtils.isAdmin()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You can only update your own reviews");
            }

            ReviewDto updated = reviewService.updateReview(id, reviewDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {
        try {
            Long userId = securityUtils.extractUserIdFromToken(authHeader);

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            ReviewDto existing = reviewService.getReviewById(id);

            // Users can only delete their own reviews
            if (!existing.getUserId().equals(userId) && !securityUtils.isAdmin()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You can only delete your own reviews");
            }

            reviewService.deleteReview(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}