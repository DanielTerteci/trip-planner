package com.trip.planner.citybreak.mapper;

import com.trip.planner.citybreak.dto.TripPlanDto;
import com.trip.planner.citybreak.models.TripPlan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TripPlanMapper {

    public static TripPlanDto toDto(TripPlan tripPlan) {
        if (tripPlan == null) return null;

        TripPlanDto dto = new TripPlanDto();
        dto.setId(tripPlan.getId());
        dto.setTitle(tripPlan.getTitle());
        dto.setUserId(tripPlan.getUser() != null ? tripPlan.getUser().getId() : null);
        dto.setDestinationIds(tripPlan.getDestinations() != null
                ? tripPlan.getDestinations().stream()
                .map(d -> d.getId())
                .collect(Collectors.toList())
                : null);
        dto.setStartDate(tripPlan.getStartDate());
        dto.setEndDate(tripPlan.getEndDate());
        dto.setBudget(BudgetMapper.toDto(tripPlan.getBudget()));
        dto.setStatus(tripPlan.getStatus() != null ? tripPlan.getStatus().name() : null);
        dto.setNotes(tripPlan.getNotes());
        dto.setCreatedAt(tripPlan.getCreatedAt());
        dto.setUpdatedAt(tripPlan.getUpdatedAt());

        return dto;
    }

    public static TripPlan toEntity(TripPlanDto tripPlanDto) {
        if (tripPlanDto == null) return null;

        return TripPlan.builder()
                .title(tripPlanDto.getTitle() != null ? tripPlanDto.getTitle() : "My Trip Plan")
                .startDate(tripPlanDto.getStartDate())
                .endDate(tripPlanDto.getEndDate())
                .budget(BudgetMapper.toEntity(tripPlanDto.getBudget()))
                .status(tripPlanDto.getStatus() != null ? TripPlan.TripStatus.valueOf(tripPlanDto.getStatus()) : TripPlan.TripStatus.DRAFT)
                .notes(tripPlanDto.getNotes())
                .createdAt(LocalDateTime.now())
                .destinations(new ArrayList<>())
                .build();
    }
}