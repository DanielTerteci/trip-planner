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
                .build();
    }

    public static TripPlan mapToTripPlan(TripPlanDto tripPlanDto, User user, Destination destination, List<Attraction> attractions) {
        return TripPlan.builder()
                .id(tripPlanDto.getId())
                .user(user)
                .destination(destination)
                .build();
    }
}
