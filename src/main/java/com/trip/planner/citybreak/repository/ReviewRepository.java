package com.trip.planner.citybreak.repository;

import com.trip.planner.citybreak.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByDestinationId(Long destinationId);
    List<Review> findByUserId(Long userId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.destination.id = :destinationId")
    Double findAverageRatingByDestinationId(@Param("destinationId") Long destinationId);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.destination.id = :destinationId")
    Long countReviewsByDestinationId(@Param("destinationId") Long destinationId);
}