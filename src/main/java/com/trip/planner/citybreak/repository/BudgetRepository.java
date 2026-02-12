package com.trip.planner.citybreak.repository;

import com.trip.planner.citybreak.models.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
//    Optional<Budget> findByTripPlanId(Long tripPlanId);
}