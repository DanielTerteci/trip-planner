package com.trip.planner.citybreak.service;

import com.trip.planner.citybreak.dto.TripPlanDto;
import com.trip.planner.citybreak.mapper.TripPlanMapper;
import com.trip.planner.citybreak.models.*;
import com.trip.planner.citybreak.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripPlanService {

    private final TripPlanRepository tripPlanRepository;
    private final UserRepository userRepository;
    private final DestinationRepository destinationRepository;
    private final TripPlanMapper tripPlanMapper;

    @Transactional
    public TripPlanDto createTripPlan(TripPlanDto tripPlanDto) {
        User user = userRepository.findById(tripPlanDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        TripPlan tripPlan = TripPlan.builder()
                .title(tripPlanDto.getTitle() != null ? tripPlanDto.getTitle() : "My Trip Plan")
                .user(user)
                .startDate(tripPlanDto.getStartDate())
                .endDate(tripPlanDto.getEndDate())
                .status(TripPlan.TripStatus.DRAFT)
                .notes(tripPlanDto.getNotes())
                .createdAt(LocalDateTime.now())
                .destinations(new ArrayList<>())
                .build();

        // Add destinations if provided
        if (tripPlanDto.getDestinationIds() != null && !tripPlanDto.getDestinationIds().isEmpty()) {
            List<Destination> destinations = tripPlanDto.getDestinationIds().stream()
                    .map(id -> destinationRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Destination not found: " + id)))
                    .collect(Collectors.toList());
            tripPlan.setDestinations(destinations);
        }

        TripPlan saved = tripPlanRepository.save(tripPlan);
        return tripPlanMapper.toDto(saved);
    }

    public TripPlanDto getTripPlanById(Long id) {
        TripPlan tripPlan = tripPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip plan not found"));
        return tripPlanMapper.toDto(tripPlan);
    }

    public List<TripPlanDto> getTripPlansByUserId(Long userId) {
        return tripPlanRepository.findByUserId(userId).stream()
                .map(tripPlanMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TripPlanDto> getAllTripPlans() {
        return tripPlanRepository.findAll().stream()
                .map(tripPlanMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public TripPlanDto updateTripPlan(Long id, TripPlanDto tripPlanDto) {
        TripPlan existing = tripPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip plan not found"));

        existing.setTitle(tripPlanDto.getTitle());
        existing.setStartDate(tripPlanDto.getStartDate());
        existing.setEndDate(tripPlanDto.getEndDate());
        existing.setNotes(tripPlanDto.getNotes());
        existing.setUpdatedAt(LocalDateTime.now());

        // Update destinations
        if (tripPlanDto.getDestinationIds() != null) {
            List<Destination> destinations = tripPlanDto.getDestinationIds().stream()
                    .map(destId -> destinationRepository.findById(destId)
                            .orElseThrow(() -> new RuntimeException("Destination not found")))
                    .collect(Collectors.toList());
            existing.setDestinations(destinations);
        }

        TripPlan updated = tripPlanRepository.save(existing);
        return tripPlanMapper.toDto(updated);
    }

    @Transactional
    public void deleteTripPlan(Long id) {
        if (!tripPlanRepository.existsById(id)) {
            throw new RuntimeException("Trip plan not found");
        }
        tripPlanRepository.deleteById(id);
    }

    @Transactional
    public TripPlanDto updateTripPlanStatus(Long id, String status) {
        TripPlan tripPlan = tripPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip plan not found"));

        tripPlan.setStatus(TripPlan.TripStatus.valueOf(status.toUpperCase()));
        tripPlan.setUpdatedAt(LocalDateTime.now());

        TripPlan updated = tripPlanRepository.save(tripPlan);
        return tripPlanMapper.toDto(updated);
    }
}