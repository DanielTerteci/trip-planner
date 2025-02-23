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

    List<TripPlan> findByDestinationId(Long destinationId);

    @Query("SELECT t FROM TripPlan t WHERE t.budget <= :maxBudget")
    List<TripPlan> findTripsByMaxBudget(@Param("maxBudget") Double maxBudget);
}