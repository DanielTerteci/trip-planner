package com.trip.planner.citybreak.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDto {
    private Long id;
    private BigDecimal totalBudget;
    private BigDecimal flightCost;
    private BigDecimal accommodationCost;
    private BigDecimal activitiesCost;
    private BigDecimal transportCost;
    private BigDecimal otherCost;
    private String currency;
    private BigDecimal estimatedTotal;
    private BigDecimal remainingBudget;
}