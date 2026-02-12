package com.trip.planner.citybreak.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "trip_itineraries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripItinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_plan_id", nullable = false)
    private TripPlan tripPlan;

    @Column(nullable = false)
    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "attraction_id")
    private Attraction attraction;

    @Column(nullable = false)
    private String activity;

    private String notes;

    private Integer orderIndex; // For sorting
}