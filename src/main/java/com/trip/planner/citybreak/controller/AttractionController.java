package com.trip.planner.citybreak.controller;

import com.trip.planner.citybreak.dto.AttractionDto;
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

    @PostMapping
    public ResponseEntity<AttractionDto> createAttraction(
            @RequestBody AttractionDto attractionDto,
            @RequestParam Long destinationId) {
        try {
            AttractionDto created = attractionService.createAttraction(attractionDto, destinationId);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AttractionDto>> getAllAttractions() {
        List<AttractionDto> attractions = attractionService.getAllAttractions();
        return ResponseEntity.ok(attractions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttractionDto> getAttractionById(@PathVariable Long id) {
        try {
            AttractionDto attraction = attractionService.getAttractionById(id);
            return ResponseEntity.ok(attraction);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/destination/{destinationId}")
    public ResponseEntity<List<AttractionDto>> getAttractionsByDestination(@PathVariable Long destinationId) {
        List<AttractionDto> attractions = attractionService.getAttractionsByDestination(destinationId);
        return ResponseEntity.ok(attractions);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<AttractionDto>> getAttractionsByType(@PathVariable String type) {
        List<AttractionDto> attractions = attractionService.getAttractionsByType(type);
        return ResponseEntity.ok(attractions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttractionDto> updateAttraction(
            @PathVariable Long id,
            @RequestBody AttractionDto attractionDto) {
        try {
            AttractionDto updated = attractionService.updateAttraction(id, attractionDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttraction(@PathVariable Long id) {
        try {
            attractionService.deleteAttraction(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}