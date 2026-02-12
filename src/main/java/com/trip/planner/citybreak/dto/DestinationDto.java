package com.trip.planner.citybreak.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DestinationDto {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String region;
    private String description;
    private Long categoryId;
    private String categoryName;
    private Double averageRating;
    private Integer reviewCount;
}