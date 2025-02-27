package com.trip.planner.citybreak.mapper;

import com.trip.planner.citybreak.dto.TripPlanDto;
import com.trip.planner.citybreak.models.Attraction;
import com.trip.planner.citybreak.models.Destination;
import com.trip.planner.citybreak.models.TripPlan;
import com.trip.planner.citybreak.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class TripPlanMapper {
    public static TripPlanDto mapToTripPlanDto(TripPlan tripPlan) {
        return TripPlanDto.builder()
                .id(tripPlan.getId())
                .userId(tripPlan.getUser().getId())
                .destinationId(tripPlan.getDestination().getId())
                .attractionIds(tripPlan.getAttractions()
                        .stream()
                        .map(Attraction::getId)
                        .collect(Collectors.toList()))
                .startDate(tripPlan.getStartDate())
                .endDate(tripPlan.getEndDate())
                .minBudget(tripPlan.getMinBudget())
                .maxBudget(tripPlan.getMaxBudget())
                .maxAttractionsPerDay(tripPlan.getMaxAttractionsPerDay())
                .attractionType(tripPlan.getAttractionType())
                .build();
    }

    public static TripPlan mapToTripPlan(TripPlanDto tripPlanDto, User user, Destination destination, List<Attraction> attractions) {
        return TripPlan.builder()
                .id(tripPlanDto.getId())
                .user(user)
                .destination(destination)
                .attractions(attractions)
                .startDate(tripPlanDto.getStartDate())
                .endDate(tripPlanDto.getEndDate())
                .minBudget(tripPlanDto.getMinBudget())
                .maxBudget(tripPlanDto.getMaxBudget())
                .maxAttractionsPerDay(tripPlanDto.getMaxAttractionsPerDay())
                .attractionType(tripPlanDto.getAttractionType())
                .build();
    }
}