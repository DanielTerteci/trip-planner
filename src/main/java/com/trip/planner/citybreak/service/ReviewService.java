package com.trip.planner.citybreak.service;

import com.trip.planner.citybreak.dto.ReviewDto;
import com.trip.planner.citybreak.mapper.ReviewMapper;
import com.trip.planner.citybreak.models.Destination;
import com.trip.planner.citybreak.models.Review;
import com.trip.planner.citybreak.models.User;
import com.trip.planner.citybreak.repository.DestinationRepository;
import com.trip.planner.citybreak.repository.ReviewRepository;
import com.trip.planner.citybreak.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final DestinationRepository destinationRepository;
    private final ReviewMapper reviewMapper;

    @Transactional
    public ReviewDto createReview(ReviewDto reviewDto) {
        User user = userRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Destination destination = destinationRepository.findById(reviewDto.getAttractionId())
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        Review review = Review.builder()
                .user(user)
                .destination(destination)
                .rating(reviewDto.getRating())
                .comment(reviewDto.getComment())
                .createdAt(LocalDateTime.now())
                .build();

        Review saved = reviewRepository.save(review);

        // Update destination rating
        updateDestinationRating(destination.getId());

        return reviewMapper.toDto(saved);
    }

    private void updateDestinationRating(Long destinationId) {
        Double avgRating = reviewRepository.findAverageRatingByDestinationId(destinationId);
        Long count = reviewRepository.countReviewsByDestinationId(destinationId);

        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        destination.setAverageRating(avgRating);
        destination.setReviewCount(count.intValue());
        destinationRepository.save(destination);
    }

    public ReviewDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return reviewMapper.toDto(review);
    }

    public List<ReviewDto> getReviewsByDestination(Long destinationId) {
        return reviewRepository.findByDestinationId(destinationId).stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ReviewDto> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserId(userId).stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReviewDto updateReview(Long id, ReviewDto reviewDto) {
        Review existing = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        existing.setRating(reviewDto.getRating());
        existing.setComment(reviewDto.getComment());

        Review updated = reviewRepository.save(existing);

        // Update destination rating
        updateDestinationRating(existing.getDestination().getId());

        return reviewMapper.toDto(updated);
    }

    @Transactional
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        Long destinationId = review.getDestination().getId();
        reviewRepository.deleteById(id);

        // Update destination rating
        updateDestinationRating(destinationId);
    }
}