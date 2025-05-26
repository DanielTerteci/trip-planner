package com.trip.planner.citybreak.service;

import com.trip.planner.citybreak.dto.RegisterDto;
import com.trip.planner.citybreak.dto.UserDto;
import com.trip.planner.citybreak.mapper.UserMapper;
import com.trip.planner.citybreak.models.User;
import com.trip.planner.citybreak.repository.UserRepository;
import com.trip.planner.citybreak.security.AuthResponse;
import com.trip.planner.citybreak.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
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
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .role("USER")
                .build();

        User savedUser = userRepository.save(user);
        System.out.println("User created successfully!");
        return UserMapper.mapToUserDto(savedUser);
    }

    private boolean isValidPassword(String password) {
        return password != null &&
                password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[!@#$%^&*(){}:;.,/~'<>]");
    }

    //METHOD TO CREATE USER BY ADMIN
//    @Transactional
//    public UserDto createUser(UserDto userDto) {
//        if(userRepository.findByEmail(userDto.getEmail()).isPresent()){
//            throw new IllegalArgumentException("Email already in use.");
//        }
//        User user = UserMapper.mapToUser(userDto);
//        User savedUser = userRepository.save(user);
//        return UserMapper.mapToUserDto(savedUser);
//    }

    public AuthResponse login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password.");
        }

        String token = jwtTokenProvider.generateToken(String.valueOf(user.getId()), user.getUsername());
        System.out.println("Login successfully: " + token);
        return new AuthResponse(token);
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

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
