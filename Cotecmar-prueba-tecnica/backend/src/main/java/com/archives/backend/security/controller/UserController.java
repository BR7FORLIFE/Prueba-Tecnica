package com.archives.backend.security.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archives.backend.security.dtos.request.LoginUserRequestDto;
import com.archives.backend.security.dtos.request.RegisterUserRequestDto;
import com.archives.backend.security.services.UserService;
import com.archives.backend.shared.utils.helper.ResponseEntityHelper;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterUserRequestDto dtoUser,
            HttpServletResponse response) {
        var result = userService.register(dtoUser, response);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginUserRequestDto dtoUser,
            HttpServletResponse response) {
        var result = userService.login(dtoUser, response);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<Map<String, Object>> testAuthorities(Authentication auth) {

        if (auth == null) {
            return ResponseEntity.status(403).body(Map.of("message", "User not Authenticate!"));
        }
        return ResponseEntity.ok().body(Map.of("Authorities", auth.getAuthorities().toString()));
    }
}
