package com.trip.planner.citybreak.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripRecommendationRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal budget;
    private String currency;
    private List<String> interests; // BEACH, ADVENTURE, CULTURAL, etc.
    private String departureCity;
    private String departureAirport;
    private Integer travelers;
}