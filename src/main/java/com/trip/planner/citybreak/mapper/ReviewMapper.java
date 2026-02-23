package com.trip.planner.citybreak.mapper;

import com.trip.planner.citybreak.dto.ReviewDto;
import com.trip.planner.citybreak.models.Review;

public class ReviewMapper {

    public static ReviewDto toDto(Review review) {
        if (review == null) return null;

        return ReviewDto.builder()
                .id(review.getId())
                .userId(review.getUser() != null ? review.getUser().getId() : null)
                .attractionId(review.getDestination() != null ? review.getDestination().getId() : null)
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt() != null ? review.getCreatedAt().toString() : null)
                .build();
    }

    public static Review toEntity(ReviewDto dto) {
        if (dto == null) return null;

        return Review.builder()
                .id(dto.getId())
                .rating(dto.getRating())
                .comment(dto.getComment())
                .build();
    }
}