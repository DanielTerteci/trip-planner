package com.trip.planner.citybreak.service;

import com.trip.planner.citybreak.dto.RegisterDto;
import com.trip.planner.citybreak.dto.UserDto;
import com.trip.planner.citybreak.mapper.UserMapper;
import com.trip.planner.citybreak.models.User;
import com.trip.planner.citybreak.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDto registerUser(RegisterDto registerDto){
        if(!isValidPassword(registerDto.getPassword())) {
            throw new IllegalArgumentException("Password must contain at least 8 characters, one uppercase letter and one special character.");
        }
        if(userRepository.findByEmail(registerDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email is already in use.");
        }

        String hashedPassword = passwordEncoder.encode(registerDto.getPassword());

        User user = User.builder()
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .role("USER")
                .build();

        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    private boolean isValidPassword(String password) {
        return password != null &&
                password.length() <= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[!@#$%^&*(){}:;.,/~'<>]");
    }

    @Transactional
    public UserDto createUser(UserDto userDto) {
        if(userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already in use.");
        }
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::mapToUserDto)
                .orElse(null);
    }

    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::mapToUserDto)
                .orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
