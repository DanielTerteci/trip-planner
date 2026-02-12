package com.trip.planner.citybreak.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "budgets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalBudget;

    private BigDecimal flightCost = BigDecimal.ZERO;

    private BigDecimal accommodationCost = BigDecimal.ZERO;

    private BigDecimal activitiesCost = BigDecimal.ZERO;

    private BigDecimal transportCost = BigDecimal.ZERO;

    private BigDecimal otherCost = BigDecimal.ZERO;

    @Column(nullable = false)
    private String currency = "USD";

    public BigDecimal getEstimatedTotal() {
        return flightCost.add(accommodationCost)
                .add(activitiesCost)
                .add(transportCost)
                .add(otherCost);
    }

    public BigDecimal getRemainingBudget() {
        if (totalBudget == null) return null;
        return totalBudget.subtract(getEstimatedTotal());
    }
}