package com.trip.planner.citybreak.mapper;

import com.trip.planner.citybreak.dto.UserDto;
import com.trip.planner.citybreak.models.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();
    }

    public static User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .role(userDto.getRole())
                .build();
    }
}
