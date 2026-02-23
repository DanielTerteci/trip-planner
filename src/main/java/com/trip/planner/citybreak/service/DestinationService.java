package com.trip.planner.citybreak.service;

import com.trip.planner.citybreak.dto.DestinationDto;
import com.trip.planner.citybreak.mapper.DestinationMapper;
import com.trip.planner.citybreak.models.Category;
import com.trip.planner.citybreak.models.Destination;
import com.trip.planner.citybreak.repository.CategoryRepository;
import com.trip.planner.citybreak.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public DestinationDto createDestination(DestinationDto destinationDto) {
        Destination destination = DestinationMapper.toEntity(destinationDto);

        if (destinationDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(destinationDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            destination.setCategory(category);
        }

        destination.setIsActive(true);
        Destination saved = destinationRepository.save(destination);
        return DestinationMapper.toDto(saved);
    }

    public DestinationDto getDestinationById(Long id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));
        return DestinationMapper.toDto(destination);
    }

    public List<DestinationDto> getAllDestinations() {
        return destinationRepository.findByIsActiveTrue().stream()
                .map(DestinationMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DestinationDto> searchDestinations(String keyword) {
        return destinationRepository.searchDestinations(keyword).stream()
                .map(DestinationMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DestinationDto> getDestinationsByCategory(Long categoryId) {
        return destinationRepository.findByCategoryId(categoryId).stream()
                .map(DestinationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public DestinationDto updateDestination(Long id, DestinationDto destinationDto) {
        Destination existing = destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        existing.setName(destinationDto.getName());
        existing.setCountry(destinationDto.getCountry());
        existing.setCity(destinationDto.getCity());
        existing.setRegion(destinationDto.getRegion());
        existing.setDescription(destinationDto.getDescription());

        if (destinationDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(destinationDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existing.setCategory(category);
        }

        Destination updated = destinationRepository.save(existing);
        return DestinationMapper.toDto(updated);
    }

    @Transactional
    public void deleteDestination(Long id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));
        destination.setIsActive(false);
        destinationRepository.save(destination);
    }
}