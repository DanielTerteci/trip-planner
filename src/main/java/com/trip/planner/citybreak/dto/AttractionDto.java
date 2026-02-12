package com.trip.planner.citybreak.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDto {
    private Long id;
    private String name;
    private Long destinationId;
    private String destinationName;
    private String description;
    private BigDecimal entryFee;
    private String type;
    private String website;
    private String openingHours;
    private Double rating;
}