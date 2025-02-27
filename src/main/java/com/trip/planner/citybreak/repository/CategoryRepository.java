package com.trip.planner.citybreak.repository;

import com.trip.planner.citybreak.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    List<Category> findByDescriptionContaining(String description);

    List<Category> findAll();

    List<Category> findByPopularityGreaterThan(int popularityThreshold);
}
