package com.trip.planner.citybreak.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        System.out.println("=== JWT FILTER START ===");
        System.out.println("Request: " + request.getMethod() + " " + request.getRequestURI());

        // Get Authorization header
        final String authHeader = request.getHeader("Authorization");
        System.out.println("Auth header: " + authHeader);

        // Check if Authorization header exists and starts with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("No Bearer token found, skipping");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Extract JWT token
            final String jwt = authHeader.substring(7);
            System.out.println("JWT Token: " + jwt.substring(0, 20) + "...");

            // Extract username (email) from JWT
            final String userEmail = jwtTokenProvider.extractUsername(jwt);
            System.out.println("Extracted email: " + userEmail);

            // If user is not already authenticated
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                System.out.println("Loading user details for: " + userEmail);

                // Load user details
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                System.out.println("User details loaded: " + userDetails.getUsername());
                System.out.println("Authorities: " + userDetails.getAuthorities());

                // Validate token
                if (jwtTokenProvider.validateToken(jwt, userDetails)) {
                    System.out.println("Token is valid!");

                    // Create authentication token WITH authorities
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    // Set authentication details
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Update security context
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    System.out.println("Authentication set in SecurityContext");
                    System.out.println("Auth object: " + SecurityContextHolder.getContext().getAuthentication());
                    System.out.println("Is authenticated: " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
                } else {
                    System.out.println("Token validation FAILED!");
                }
            } else {
                System.out.println("User already authenticated or email is null");
            }
        } catch (Exception e) {
            System.out.println("ERROR in JWT filter: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("=== JWT FILTER END ===");
        filterChain.doFilter(request, response);
    }
}