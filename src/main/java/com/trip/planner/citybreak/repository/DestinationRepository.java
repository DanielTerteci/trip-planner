package com.trip.planner.citybreak.repository;

import com.trip.planner.citybreak.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
//    List<Destination> findByCountry(String country);
//    List<Destination> findByCity(String city);
    List<Destination> findByCategoryId(Long categoryId);
    List<Destination> findByIsActiveTrue();

    @Query("SELECT d FROM Destination d WHERE " +
            "LOWER(d.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(d.country) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(d.city) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Destination> searchDestinations(@Param("keyword") String keyword);
}