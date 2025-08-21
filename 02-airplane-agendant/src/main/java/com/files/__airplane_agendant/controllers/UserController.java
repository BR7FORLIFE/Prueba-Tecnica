package com.files.__airplane_agendant.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.files.__airplane_agendant.Dtos.users.AuthenticationRequest;
import com.files.__airplane_agendant.models.User;
import com.files.__airplane_agendant.services.UserServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController("/api/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserServices userServices;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/allusers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userServices.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/save/{user}")
    public ResponseEntity<Void> saveUser(@Valid @RequestBody AuthenticationRequest userRequest){
        userServices.saveUser(userRequest);
        return ResponseEntity.noContent().build();
    }
}
