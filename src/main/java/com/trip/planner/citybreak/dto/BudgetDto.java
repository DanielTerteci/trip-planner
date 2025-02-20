package com.trip.planner.citybreak.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDto {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private Long tripPlanId;
    private BigDecimal totalBudget;
    private String currency;
}
