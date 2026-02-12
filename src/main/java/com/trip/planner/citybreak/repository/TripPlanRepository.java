package com.trip.planner.citybreak.repository;

import com.trip.planner.citybreak.models.TripPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripPlanRepository extends JpaRepository<TripPlan, Long> {
    List<TripPlan> findByUserId(Long userId);
//    List<TripPlan> findByUserIdAndStatus(Long userId, TripPlan.TripStatus status);

//    @Query("SELECT t FROM TripPlan t WHERE t.user.id = :userId ORDER BY t.createdAt DESC")
//    List<TripPlan> findByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId);
}