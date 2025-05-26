package com.trip.planner.citybreak.repository;

import com.trip.planner.citybreak.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByFirstName(String firstName);
    List<User> findByEmailAndRole(String email, String role);
    Optional<User> findByUsername(String username);
}
