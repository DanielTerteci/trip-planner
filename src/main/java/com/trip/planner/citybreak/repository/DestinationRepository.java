package com.trip.planner.citybreak.repository;

import com.trip.planner.citybreak.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    List<Destination> findByCityName(String name);

    List<Destination> findByNameContaining(String name);

    List<Destination> findByCountry(String country);
}