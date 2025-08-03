package com.todo.ToDo.security.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.ToDo.models.UserModel;
import com.todo.ToDo.repository.UserRepository;
import com.todo.ToDo.security.Dtos.AuthenticationRequest;
import com.todo.ToDo.security.Dtos.AuthenticationResponse;
import com.todo.ToDo.security.jwt.Jwt;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServices {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final Jwt jwt;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(AuthenticationRequest request) {
        UserModel user = new UserModel(request.getId(), request.getUsername(),
                passwordEncoder.encode(request.getPassword()), request.getRol());
        userRepository.saveUser(user);
        String token = jwt.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserModel user = userRepository.getUserById(request.getId());
        String token = jwt.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
