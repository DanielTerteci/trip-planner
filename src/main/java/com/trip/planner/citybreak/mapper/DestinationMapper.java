package com.trip.planner.citybreak.mapper;

import com.trip.planner.citybreak.dto.DestinationDto;
import com.trip.planner.citybreak.models.Destination;

public class DestinationMapper {
    public static DestinationDto mapToDestinationDto(Destination destination) {
        return DestinationDto.builder()
                .id(destination.getId())
                .destinationName(destination.getDestinationName())
                .cityName(destination.getCityName())
                .country(destination.getCountryName())
                .description(destination.getDescription())
                .build();
    }

    public static Destination mapToDestination(DestinationDto destinationDto) {
        return Destination.builder()
                .id(destinationDto.getId())
                .destinationName(destinationDto.getDestinationName())
                .cityName(destinationDto.getCityName())
                .countryName(destinationDto.getCountry())
                .description(destinationDto.getDescription())
                .build();
    }
}
