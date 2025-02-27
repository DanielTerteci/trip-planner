package com.trip.planner.citybreak.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripPlanDto {
    private Long id;
    private Long userId;
    private Long destinationId;
    private List<Long> attractionIds;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal minBudget;
    private BigDecimal maxBudget;
    private int maxAttractionsPerDay;
    private String attractionType;
}
