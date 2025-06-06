package com.trip.planner.citybreak.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDto {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String type;
    private String description;
    private String destination;
    private String location;
}
