package com.trip.planner.citybreak.repository;

import com.trip.planner.citybreak.models.TripPlan;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripPlanRepository extends JpaRepository<TripPlan, Long> {
    List<TripPlan> findByStartDateBetween(LocalDate startDate, Local endDate);

    List<TripPlan> findByDestination_CityName(Long destinationId);

    List<TripPlan> findByBudgetBetween(BigDecimal minBudget, BigDecimal maxBudget);

    List<TripPlan> findByAttraction_Type(String type);

    List<TripPlan> findByAttractionsPerDay(int maxAttractionsPerDay);
}