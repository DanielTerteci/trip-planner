package com.trip.planner.citybreak.service;

import com.trip.planner.citybreak.dto.DestinationDto;
import com.trip.planner.citybreak.mapper.DestinationMapper;
import com.trip.planner.citybreak.models.Destination;
import com.trip.planner.citybreak.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationService {
    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public DestinationDto createDestination(DestinationDto destinationDto) {
        Destination destination = DestinationMapper.mapToDestination(destinationDto);
        Destination savedDestination = destinationRepository.save(destination);
        return DestinationMapper.mapToDestinationDto(savedDestination);
    }

    public DestinationDto getDestinationById(Long id) {
        return destinationRepository.findById(id)
                .map(DestinationMapper::mapToDestinationDto)
                .orElse(null);
    }

    public List<DestinationDto> getDestinationsByCity(String city) {
        List<Destination> destinations = destinationRepository.findByCityName(city);
        return destinations.stream().map(DestinationMapper::mapToDestinationDto).collect(Collectors.toList());
    }

    public List<DestinationDto> getDestinationsByCountry(String country) {
        List<Destination> destinations = destinationRepository.findByCountryName(country);
        return destinations.stream().map(DestinationMapper::mapToDestinationDto).collect(Collectors.toList());
    }

    public void deleteDestination(Long id) {
        destinationRepository.deleteById(id);
    }

}

