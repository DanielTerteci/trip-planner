package com.trip.planner.citybreak.mapper;

import com.trip.planner.citybreak.dto.CategoryDto;
import com.trip.planner.citybreak.models.Category;

public class CategoryMapper {

    public static CategoryDto mapToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setPopularity(category.getPopularity());
        return categoryDto;
    }

    public static Category mapToCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setPopularity(categoryDto.getPopularity());
        return category;
    }
}
