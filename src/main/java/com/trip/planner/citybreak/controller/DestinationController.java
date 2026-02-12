package com.trip.planner.citybreak.controller;

import com.trip.planner.citybreak.dto.DestinationDto;
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

    @PostMapping
    public ResponseEntity<DestinationDto> createDestination(@RequestBody DestinationDto destinationDto) {
        DestinationDto created = destinationService.createDestination(destinationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<DestinationDto>> getAllDestinations() {
        List<DestinationDto> destinations = destinationService.getAllDestinations();
        return ResponseEntity.ok(destinations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationDto> getDestinationById(@PathVariable Long id) {
        try {
            DestinationDto destination = destinationService.getDestinationById(id);
            return ResponseEntity.ok(destination);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<DestinationDto>> searchDestinations(@RequestParam String keyword) {
        List<DestinationDto> destinations = destinationService.searchDestinations(keyword);
        return ResponseEntity.ok(destinations);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<DestinationDto>> getDestinationsByCategory(@PathVariable Long categoryId) {
        List<DestinationDto> destinations = destinationService.getDestinationsByCategory(categoryId);
        return ResponseEntity.ok(destinations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinationDto> updateDestination(
            @PathVariable Long id,
            @RequestBody DestinationDto destinationDto) {
        try {
            DestinationDto updated = destinationService.updateDestination(id, destinationDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable Long id) {
        try {
            destinationService.deleteDestination(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}