package com.trip.planner.citybreak.service;

import com.trip.planner.citybreak.dto.TripPlanDto;
import com.trip.planner.citybreak.mapper.TripPlanMapper;
import com.trip.planner.citybreak.repository.TripPlanRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripPlanService {
    private final TripPlanRepository tripPlanRepository;

    public TripPlanService(TripPlanRepository tripPlanRepository) {
        this.tripPlanRepository = tripPlanRepository;
    }

    public List<TripPlanDto> getTripPlansByBudget(BigDecimal minBudget, BigDecimal maxBudget) {
        return tripPlanRepository.findByMinBudgetBetween(minBudget, maxBudget)
                .stream()
                .map(TripPlanMapper::mapToTripPlanDto)
                .collect(Collectors.toList());
    }

    public List<TripPlanDto> getTripPlansByAttractionType(String type) {
        return tripPlanRepository.findByAttractions_Type(type)
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

    public List<TripPlanDto> getTripPlansByCity(String cityName) {
        return tripPlanRepository.findByDestination_CityName(cityName)
                .stream()
                .map(TripPlanMapper::mapToTripPlanDto)
                .collect(Collectors.toList());
    }

    public List<TripPlanDto> getTripPlansByDuration(LocalDate minDays, LocalDate maxDays) {
        return tripPlanRepository.findByStartDateBetween(minDays, maxDays)
                .stream()
                .map(TripPlanMapper::mapToTripPlanDto)
                .collect(Collectors.toList());
    }
}
