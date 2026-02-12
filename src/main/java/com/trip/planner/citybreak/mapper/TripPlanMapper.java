package com.trip.planner.citybreak.mapper;

import com.trip.planner.citybreak.dto.TripPlanDto;
import com.trip.planner.citybreak.models.TripPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class TripPlanMapper {

    @Autowired
    private BudgetMapper budgetMapper;

    public TripPlanDto toDto(TripPlan tripPlan) {
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
        dto.setBudget(budgetMapper.toDto(tripPlan.getBudget()));
        dto.setStatus(tripPlan.getStatus() != null ? tripPlan.getStatus().name() : null);
        dto.setNotes(tripPlan.getNotes());
        dto.setCreatedAt(tripPlan.getCreatedAt());
        dto.setUpdatedAt(tripPlan.getUpdatedAt());

        return dto;
    }

    public TripPlan toEntity(TripPlanDto dto) {
        if (dto == null) return null;

        TripPlan tripPlan = new TripPlan();
        tripPlan.setId(dto.getId());
        tripPlan.setTitle(dto.getTitle());
        tripPlan.setStartDate(dto.getStartDate());
        tripPlan.setEndDate(dto.getEndDate());
        tripPlan.setNotes(dto.getNotes());

        return tripPlan;
    }
}