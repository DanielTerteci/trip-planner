package com.trip.planner.citybreak.controller;

import com.trip.planner.citybreak.dto.AttractionDto;
import com.trip.planner.citybreak.security.SecurityUtils;
import com.trip.planner.citybreak.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attractions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AttractionController {

    private final AttractionService attractionService;
    private final SecurityUtils securityUtils;

    @PostMapping
    public ResponseEntity<?> createAttraction(
            @RequestBody AttractionDto attractionDto,
            @RequestParam Long destinationId) {
        // Only admins can create attractions
        if (!securityUtils.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied. Only admins can create attractions.");
        }

        try {
            AttractionDto created = attractionService.createAttraction(attractionDto, destinationId);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AttractionDto>> getAllAttractions() {
        // Public endpoint
        List<AttractionDto> attractions = attractionService.getAllAttractions();
        return ResponseEntity.ok(attractions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAttractionById(@PathVariable Long id) {
        // Public endpoint
        try {
            AttractionDto attraction = attractionService.getAttractionById(id);
            return ResponseEntity.ok(attraction);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/destination/{destinationId}")
    public ResponseEntity<List<AttractionDto>> getAttractionsByDestination(@PathVariable Long destinationId) {
        // Public endpoint
        List<AttractionDto> attractions = attractionService.getAttractionsByDestination(destinationId);
        return ResponseEntity.ok(attractions);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getAttractionsByType(@PathVariable String type) {
        // Public endpoint
        try {
            List<AttractionDto> attractions = attractionService.getAttractionsByType(type);
            return ResponseEntity.ok(attractions);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAttraction(
            @PathVariable Long id,
            @RequestBody AttractionDto attractionDto) {
        // Only admins can update attractions
        if (!securityUtils.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied. Only admins can update attractions.");
        }

        try {
            AttractionDto updated = attractionService.updateAttraction(id, attractionDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttraction(@PathVariable Long id) {
        // Only admins can delete attractions
        if (!securityUtils.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied. Only admins can delete attractions.");
        }

        try {
            attractionService.deleteAttraction(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}