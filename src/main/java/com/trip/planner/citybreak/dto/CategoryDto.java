package com.trip.planner.citybreak.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    @Min(value = 1, message = "Popularity must be between 1 and 5.")
    @Max(value = 5, message = "Popularity must be between 1 and 5.")
    private int popularity;

}
