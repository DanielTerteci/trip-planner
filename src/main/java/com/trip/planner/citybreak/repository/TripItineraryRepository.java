package com.trip.planner.citybreak.repository;

import com.trip.planner.citybreak.models.TripItinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripItineraryRepository extends JpaRepository<TripItinerary, Long> {
//    List<TripItinerary> findByTripPlanId(Long tripPlanId);
//    List<TripItinerary> findByTripPlanIdOrderByDateAscOrderIndexAsc(Long tripPlanId);
//    List<TripItinerary> findByTripPlanIdAndDate(Long tripPlanId, LocalDate date);
}