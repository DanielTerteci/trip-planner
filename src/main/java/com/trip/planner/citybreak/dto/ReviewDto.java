package com.trip.planner.citybreak.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private Long attractionId;
    private int rating;
    private String comment;
    private String createdAt;
}
