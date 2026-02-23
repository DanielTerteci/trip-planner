package com.trip.planner.citybreak.mapper;

import com.trip.planner.citybreak.dto.CategoryDto;
import com.trip.planner.citybreak.models.Category;

public class CategoryMapper {

    public static CategoryDto toDto(Category category) {
        if (category == null) return null;

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .popularity(category.getPopularity())
                .build();
    }

    public static Category toEntity(CategoryDto dto) {
        if (dto == null) return null;

        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .popularity(dto.getPopularity())
                .build();
    }
}