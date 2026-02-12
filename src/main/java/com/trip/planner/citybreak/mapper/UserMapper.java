package com.trip.planner.citybreak.mapper;

import com.trip.planner.citybreak.dto.UserDto;
import com.trip.planner.citybreak.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setSubscriptionPlan(user.getSubscriptionPlan() != null ? user.getSubscriptionPlan().name() : null);
        dto.setSubscriptionExpiry(user.getSubscriptionExpiry());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setPro(user.isPro());

        return dto;
    }

    public User toEntity(UserDto dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        return user;
    }
}