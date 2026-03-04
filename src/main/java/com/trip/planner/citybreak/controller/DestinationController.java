package com.trip.planner.citybreak.controller;

import com.trip.planner.citybreak.dto.DestinationDto;
import com.trip.planner.citybreak.security.SecurityUtils;
import com.trip.planner.citybreak.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DestinationController {

    private final DestinationService destinationService;
    private final SecurityUtils securityUtils;

    @PostMapping
    public ResponseEntity<?> createDestination(@RequestBody DestinationDto destinationDto) {
        // Only admins can create destinations
        if (!securityUtils.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied. Only admins can create destinations.");
        }

        DestinationDto created = destinationService.createDestination(destinationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<DestinationDto>> getAllDestinations() {
        // Public endpoint - anyone can view destinations
        List<DestinationDto> destinations = destinationService.getAllDestinations();
        return ResponseEntity.ok(destinations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDestinationById(@PathVariable Long id) {
        // Public endpoint - anyone can view a destination
        try {
            DestinationDto destination = destinationService.getDestinationById(id);
            return ResponseEntity.ok(destination);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<DestinationDto>> searchDestinations(@RequestParam String keyword) {
        // Public endpoint - anyone can search
        List<DestinationDto> destinations = destinationService.searchDestinations(keyword);
        return ResponseEntity.ok(destinations);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<DestinationDto>> getDestinationsByCategory(@PathVariable Long categoryId) {
        // Public endpoint - anyone can filter by category
        List<DestinationDto> destinations = destinationService.getDestinationsByCategory(categoryId);
        return ResponseEntity.ok(destinations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDestination(
            @PathVariable Long id,
            @RequestBody DestinationDto destinationDto) {
        // Only admins can update destinations
        if (!securityUtils.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied. Only admins can update destinations.");
        }

        try {
            DestinationDto updated = destinationService.updateDestination(id, destinationDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDestination(@PathVariable Long id) {
        // Only admins can delete destinations
        if (!securityUtils.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied. Only admins can delete destinations.");
        }

        try {
            destinationService.deleteDestination(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}