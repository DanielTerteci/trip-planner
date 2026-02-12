package com.trip.planner.citybreak.mapper;

import com.trip.planner.citybreak.dto.CategoryDto;
import com.trip.planner.citybreak.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category category) {
        if (category == null) return null;

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .popularity(category.getPopularity())
                .build();
    }

    public Category toEntity(CategoryDto dto) {
        if (dto == null) return null;

        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .popularity(dto.getPopularity())
                .build();
    }
}