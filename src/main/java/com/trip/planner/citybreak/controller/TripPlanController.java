package com.trip.planner.citybreak.controller;

import com.trip.planner.citybreak.dto.TripPlanDto;
import com.trip.planner.citybreak.service.TripPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trip-plans")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TripPlanController {

    private final TripPlanService tripPlanService;

    @PostMapping
    public ResponseEntity<TripPlanDto> createTripPlan(@RequestBody TripPlanDto tripPlanDto) {
        try {
            TripPlanDto created = tripPlanService.createTripPlan(tripPlanDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getAllTripPlans")
    public ResponseEntity<List<TripPlanDto>> getAllTripPlans() {
        List<TripPlanDto> tripPlans = tripPlanService.getAllTripPlans();
        return ResponseEntity.ok(tripPlans);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<TripPlanDto> getTripPlanById(@PathVariable Long id) {
        try {
            TripPlanDto tripPlan = tripPlanService.getTripPlanById(id);
            return ResponseEntity.ok(tripPlan);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TripPlanDto>> getTripPlansByUser(@PathVariable Long userId) {
        List<TripPlanDto> tripPlans = tripPlanService.getTripPlansByUserId(userId);
        return ResponseEntity.ok(tripPlans);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TripPlanDto> updateTripPlan(
            @PathVariable Long id,
            @RequestBody TripPlanDto tripPlanDto) {
        try {
            TripPlanDto updated = tripPlanService.updateTripPlan(id, tripPlanDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TripPlanDto> updateTripPlanStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        try {
            TripPlanDto updated = tripPlanService.updateTripPlanStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTripPlan(@PathVariable Long id) {
        try {
            tripPlanService.deleteTripPlan(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}