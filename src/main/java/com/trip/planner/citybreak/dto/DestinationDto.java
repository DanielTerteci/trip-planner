package com.trip.planner.citybreak.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinationDto {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String destinationName;
    private String cityName;
    private String country;
    private String description;
}
