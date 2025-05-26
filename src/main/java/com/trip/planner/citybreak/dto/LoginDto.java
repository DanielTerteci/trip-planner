package com.trip.planner.citybreak.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String username;
    private String password;
}
