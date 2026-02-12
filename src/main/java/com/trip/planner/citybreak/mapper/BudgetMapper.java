package com.trip.planner.citybreak.mapper;

import com.trip.planner.citybreak.dto.BudgetDto;
import com.trip.planner.citybreak.models.Budget;
import org.springframework.stereotype.Component;

@Component
public class BudgetMapper {

    public BudgetDto toDto(Budget budget) {
        if (budget == null) return null;

        BudgetDto dto = new BudgetDto();
        dto.setId(budget.getId());
        dto.setTotalBudget(budget.getTotalBudget());
        dto.setFlightCost(budget.getFlightCost());
        dto.setAccommodationCost(budget.getAccommodationCost());
        dto.setActivitiesCost(budget.getActivitiesCost());
        dto.setTransportCost(budget.getTransportCost());
        dto.setOtherCost(budget.getOtherCost());
        dto.setCurrency(budget.getCurrency());
        dto.setEstimatedTotal(budget.getEstimatedTotal());
        dto.setRemainingBudget(budget.getRemainingBudget());

        return dto;
    }

    public Budget toEntity(BudgetDto dto) {
        if (dto == null) return null;

        Budget budget = new Budget();
        budget.setId(dto.getId());
        budget.setTotalBudget(dto.getTotalBudget());
        budget.setFlightCost(dto.getFlightCost());
        budget.setAccommodationCost(dto.getAccommodationCost());
        budget.setActivitiesCost(dto.getActivitiesCost());
        budget.setTransportCost(dto.getTransportCost());
        budget.setOtherCost(dto.getOtherCost());
        budget.setCurrency(dto.getCurrency());

        return budget;
    }
}