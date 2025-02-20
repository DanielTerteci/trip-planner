package com.trip.planner.citybreak.dto;

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
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private Long destinationId;
    private List<Long> attractions;
    private LocalDate startDate;
    private LocalDate endDate;
}
