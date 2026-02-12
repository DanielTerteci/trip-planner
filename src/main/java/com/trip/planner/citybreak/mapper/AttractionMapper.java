package com.trip.planner.citybreak.mapper;

import com.trip.planner.citybreak.dto.AttractionDto;
import com.trip.planner.citybreak.models.Attraction;
import org.springframework.stereotype.Component;

@Component
public class AttractionMapper {

    public AttractionDto toDto(Attraction attraction) {
        if (attraction == null) return null;

        AttractionDto dto = new AttractionDto();
        dto.setId(attraction.getId());
        dto.setName(attraction.getName());
        dto.setDestinationId(attraction.getDestination() != null ? attraction.getDestination().getId() : null);
        dto.setDestinationName(attraction.getDestination() != null ? attraction.getDestination().getName() : null);
        dto.setDescription(attraction.getDescription());
        dto.setEntryFee(attraction.getEntryFee());
        dto.setType(attraction.getType() != null ? attraction.getType().name() : null);
        dto.setWebsite(attraction.getWebsite());
        dto.setOpeningHours(attraction.getOpeningHours());
        dto.setRating(attraction.getRating());

        return dto;
    }

    public Attraction toEntity(AttractionDto dto) {
        if (dto == null) return null;

        Attraction attraction = new Attraction();
        attraction.setId(dto.getId());
        attraction.setName(dto.getName());
        attraction.setDescription(dto.getDescription());
        attraction.setEntryFee(dto.getEntryFee());
        attraction.setWebsite(dto.getWebsite());
        attraction.setOpeningHours(dto.getOpeningHours());
        attraction.setRating(dto.getRating());

        return attraction;
    }
}