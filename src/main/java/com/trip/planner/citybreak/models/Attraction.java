package com.trip.planner.citybreak.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "attractions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    @Column(length = 2000)
    private String description;

    private BigDecimal entryFee;

    @Enumerated(EnumType.STRING)
    private AttractionType type;

    private String website;

    private String openingHours;

    private Double rating;

    public enum AttractionType {
        MUSEUM, PARK, RESTAURANT, LANDMARK, ENTERTAINMENT,
        SHOPPING, BEACH, HISTORICAL, RELIGIOUS, NATURE
    }
}