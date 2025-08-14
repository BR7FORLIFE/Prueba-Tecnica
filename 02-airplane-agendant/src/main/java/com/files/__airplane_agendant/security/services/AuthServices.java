package com.files.__airplane_agendant.security.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.files.__airplane_agendant.Dtos.users.AuthenticationRequest;
import com.files.__airplane_agendant.Dtos.users.AuthenticationResponse;
import com.files.__airplane_agendant.models.User;
import com.files.__airplane_agendant.repository.UserRepository;
import com.files.__airplane_agendant.security.jwt.Jwt;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Jwt jwt;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(AuthenticationRequest request) {

        User user = User.builder()
                .id(request.getId())
                .identification(request.getIdentification())
                .passport(request.getPassport())
                .password(passwordEncoder.encode(request.getPassword()))
                .reservations(null)
                .roles(null)
                .username(request.getUsername())
                .build();

        userRepository.save(user);
        String response = jwt.generateToken(user);

        return new AuthenticationResponse(response);
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));

        String userToken = jwt.generateToken(user);

        return new AuthenticationResponse(userToken);
    }
}
