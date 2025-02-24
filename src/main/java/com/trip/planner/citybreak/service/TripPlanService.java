package com.trip.planner.citybreak.service;

import com.trip.planner.citybreak.dto.TripPlanDto;
import com.trip.planner.citybreak.mapper.TripPlanMapper;
import com.trip.planner.citybreak.repository.TripPlanRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripPlanService {
    private final TripPlanRepository tripPlanRepository;

    public TripPlanService(TripPlanRepository tripPlanRepository) {
        this.tripPlanRepository = tripPlanRepository;
    }

    public List<TripPlanDto> getTripPlansByBudget(BigDecimal minBudget, BigDecimal maxBudget) {
        return tripPlanRepository.findByBudgetBetween(minBudget, maxBudget)
                .stream()
                .map(TripPlanMapper::mapToTripPlanDto)
                .collect(Collectors.toList());
    }

    public List<TripPlanDto> getTripPlansByAttractionType(String type) {
        return tripPlanRepository.findByAttraction_Type(type)
                .stream()
                .map(TripPlanMapper::mapToTripPlanDto)
                .collect(Collectors.toList());
    }

    public List<TripPlanDto> getTripPlansByAttractionsPerDay(int maxAttractionsPerDay) {
        return tripPlanRepository.findByAttractionsPerDay(maxAttractionsPerDay)
                .stream()
                .map(TripPlanMapper::mapToTripPlanDto)
                .collect(Collectors.toList());
    }
}
