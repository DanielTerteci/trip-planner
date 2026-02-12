package com.trip.planner.citybreak.service;

import com.trip.planner.citybreak.dto.AttractionDto;
import com.trip.planner.citybreak.mapper.AttractionMapper;
import com.trip.planner.citybreak.models.Attraction;
import com.trip.planner.citybreak.models.Destination;
import com.trip.planner.citybreak.repository.AttractionRepository;
import com.trip.planner.citybreak.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;
    private final DestinationRepository destinationRepository;
    private final AttractionMapper attractionMapper;

    @Transactional
    public AttractionDto createAttraction(AttractionDto attractionDto, Long destinationId) {
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        Attraction attraction = attractionMapper.toEntity(attractionDto);
        attraction.setDestination(destination);

        if (attractionDto.getType() != null) {
            attraction.setType(Attraction.AttractionType.valueOf(attractionDto.getType()));
        }

        Attraction saved = attractionRepository.save(attraction);
        return attractionMapper.toDto(saved);
    }

    public AttractionDto getAttractionById(Long id) {
        Attraction attraction = attractionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attraction not found"));
        return attractionMapper.toDto(attraction);
    }

    public List<AttractionDto> getAllAttractions() {
        return attractionRepository.findAll().stream()
                .map(attractionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AttractionDto> getAttractionsByDestination(Long destinationId) {
        return attractionRepository.findByDestinationId(destinationId).stream()
                .map(attractionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AttractionDto> getAttractionsByType(String type) {
        Attraction.AttractionType attractionType = Attraction.AttractionType.valueOf(type.toUpperCase());
        return attractionRepository.findByType(attractionType).stream()
                .map(attractionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AttractionDto updateAttraction(Long id, AttractionDto attractionDto) {
        Attraction existing = attractionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attraction not found"));

        existing.setName(attractionDto.getName());
        existing.setDescription(attractionDto.getDescription());
        existing.setEntryFee(attractionDto.getEntryFee());
        existing.setWebsite(attractionDto.getWebsite());
        existing.setOpeningHours(attractionDto.getOpeningHours());
        existing.setRating(attractionDto.getRating());

        if (attractionDto.getType() != null) {
            existing.setType(Attraction.AttractionType.valueOf(attractionDto.getType()));
        }

        Attraction updated = attractionRepository.save(existing);
        return attractionMapper.toDto(updated);
    }

    @Transactional
    public void deleteAttraction(Long id) {
        if (!attractionRepository.existsById(id)) {
            throw new RuntimeException("Attraction not found");
        }
        attractionRepository.deleteById(id);
    }
}