package com.trip.planner.citybreak.mapper;

import com.trip.planner.citybreak.dto.DestinationDto;
import com.trip.planner.citybreak.models.Destination;
import org.springframework.stereotype.Component;

@Component
public class DestinationMapper {

    public DestinationDto toDto(Destination destination) {
        if (destination == null) return null;

        DestinationDto dto = new DestinationDto();
        dto.setId(destination.getId());
        dto.setName(destination.getName());
        dto.setCountry(destination.getCountry());
        dto.setCity(destination.getCity());
        dto.setRegion(destination.getRegion());
        dto.setDescription(destination.getDescription());
        dto.setCategoryId(destination.getCategory() != null ? destination.getCategory().getId() : null);
        dto.setCategoryName(destination.getCategory() != null ? destination.getCategory().getName() : null);
        dto.setAverageRating(destination.getAverageRating());
        dto.setReviewCount(destination.getReviewCount());

        return dto;
    }

    public Destination toEntity(DestinationDto dto) {
        if (dto == null) return null;

        Destination destination = new Destination();
        destination.setId(dto.getId());
        destination.setName(dto.getName());
        destination.setCountry(dto.getCountry());
        destination.setCity(dto.getCity());
        destination.setRegion(dto.getRegion());
        destination.setDescription(dto.getDescription());

        return destination;
    }
}