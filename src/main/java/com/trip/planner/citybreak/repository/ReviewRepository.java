package com.trip.planner.citybreak.repository;

import com.trip.planner.citybreak.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAttractionId(Long attractionId);
    List<Review> findByRating(Integer rating);
}