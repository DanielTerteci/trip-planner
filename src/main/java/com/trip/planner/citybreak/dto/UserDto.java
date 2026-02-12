package com.trip.planner.citybreak.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String subscriptionPlan;
    private LocalDateTime subscriptionExpiry;
    private LocalDateTime createdAt;
    private boolean isPro;
}