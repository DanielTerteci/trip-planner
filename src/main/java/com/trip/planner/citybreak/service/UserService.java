package com.trip.planner.citybreak.service;

import com.trip.planner.citybreak.dto.RegisterDto;
import com.trip.planner.citybreak.dto.UserDto;
import com.trip.planner.citybreak.mapper.UserMapper;
import com.trip.planner.citybreak.models.User;
import com.trip.planner.citybreak.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    // Password validation pattern: minimum 8 characters and at least 2 special characters
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[!@#$%^&*()\\-_=+\\[\\]{};:'\",.<>/?\\\\|`~]");

    @Transactional
    public UserDto registerUser(RegisterDto registerDto) {
        // Validate passwords match
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        // Validate password length (minimum 8 characters)
        if (registerDto.getPassword().length() < 8) {
            throw new RuntimeException("Password must be at least 8 characters long");
        }

        // Validate password has at least 2 special characters
        long specialCharCount = registerDto.getPassword().chars()
                .filter(c -> SPECIAL_CHAR_PATTERN.matcher(String.valueOf((char) c)).matches())
                .count();

        if (specialCharCount < 2) {
            throw new RuntimeException("Password must contain at least 2 special characters (!@#$%^&*()-_=+[]{}; etc.)");
        }

        // Check if email exists
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .subscriptionPlan(User.SubscriptionPlan.BASIC)
                .role(User.UserRole.USER)
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return userMapper.toDto(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto upgradeToPro(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setSubscriptionPlan(User.SubscriptionPlan.PRO);
        user.setSubscriptionExpiry(LocalDateTime.now().plusMonths(1)); // 1 month subscription
        user.setUpdatedAt(LocalDateTime.now());

        User updated = userRepository.save(user);
        return userMapper.toDto(updated);
    }

    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setFirstName(userDto.getFirstName());
        existing.setLastName(userDto.getLastName());
        existing.setUpdatedAt(LocalDateTime.now());

        User updated = userRepository.save(existing);
        return userMapper.toDto(updated);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}