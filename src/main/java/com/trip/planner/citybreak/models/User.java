package com.trip.planner.citybreak.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.processing.Pattern;

@Data
@Entity
@Builder
@Table(name = "users_table")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

//    @Size(min = 8, message = "Password must be at least 8 characters long")
//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()-_=+{};:',<.>/?]).*$",
//            message = "Password must contain at least one capital letter and one symbol")
//    @NotBlank
    private String password;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "role", nullable = false)
    private String role;

}
