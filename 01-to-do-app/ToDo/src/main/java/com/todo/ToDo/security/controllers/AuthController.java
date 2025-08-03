package com.todo.ToDo.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todo.ToDo.security.Dtos.AuthenticationRequest;
import com.todo.ToDo.security.Dtos.AuthenticationResponse;
import com.todo.ToDo.security.services.AuthServices;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServices authServices;

    @GetMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse> register(AuthenticationRequest request) {
        AuthenticationResponse response = authServices.register(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {
        AuthenticationResponse response = authServices.login(request);
        return ResponseEntity.ok().body(response);
    }
}
