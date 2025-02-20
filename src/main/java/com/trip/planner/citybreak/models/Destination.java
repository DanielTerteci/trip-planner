package com.trip.planner.citybreak.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@Table(name = "destinations")
@NoArgsConstructor
@AllArgsConstructor
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "destination_name", nullable = false)
    private String destinationName;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @Column(name = "description")
    private String description;
}