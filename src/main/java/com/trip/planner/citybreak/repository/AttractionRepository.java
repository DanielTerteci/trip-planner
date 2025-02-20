package com.trip.planner.citybreak.repository;

import com.trip.planner.citybreak.models.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    List<Attraction> findByDestinationId(Long destinationId);
    List<Attraction> findByName(String name);
}
