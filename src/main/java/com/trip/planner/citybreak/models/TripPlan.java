package com.trip.planner.citybreak.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "trip_plan")
@NoArgsConstructor
@AllArgsConstructor
public class TripPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    @ManyToMany
    @JoinTable(
            name = "trip_plan_attractions",
            joinColumns = @JoinColumn(name = "trip_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "attraction_id")
    )
    private List<Attraction> attractions;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "min_budget", nullable = false)
    private BigDecimal minBudget;

    @Column(name = "max_budget", nullable = false)
    private BigDecimal maxBudget;

    @Column(name = "max_attractions_per_day", nullable = false)
    private int maxAttractionsPerDay;

    @Column(name = "attraction_type", nullable = false)
    private String attractionType;
}