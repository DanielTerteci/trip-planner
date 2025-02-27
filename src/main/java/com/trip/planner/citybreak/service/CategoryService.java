package com.trip.planner.citybreak.service;

import com.trip.planner.citybreak.dto.CategoryDto;
import com.trip.planner.citybreak.mapper.CategoryMapper;
import com.trip.planner.citybreak.models.Category;
import com.trip.planner.citybreak.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto getCategoryByName(String name) {
        Optional<Category> category = categoryRepository.findByName(name);
        if (category.isPresent()) {
            return CategoryMapper.mapToCategoryDto(category.get());
        } else {
            throw new IllegalArgumentException("Category not found with name: " + name);
        }
    }

    public List<CategoryDto> getCategoriesByDescription(String description) {
        List<Category> categories = categoryRepository.findByDescriptionContaining(description);
        return categories.stream()
                .map(CategoryMapper::mapToCategoryDto)
                .collect(Collectors.toList());
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper::mapToCategoryDto)
                .collect(Collectors.toList());
    }

    public List<CategoryDto> getCategoriesByPopularity(int popularityThreshold) {
        List<Category> categories = categoryRepository.findByPopularityGreaterThan(popularityThreshold);
        return categories.stream()
                .map(CategoryMapper::mapToCategoryDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.mapToCategory(categoryDto);
        if (category.getPopularity() < 1 || category.getPopularity() > 5) {
            throw new IllegalArgumentException("Popularity must be between 1 and 5.");
        }
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}