package com.archives.backend.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> register(@RequestBody @Valid RegisterUserRequestDto dtoUser, HttpServletResponse response) {
        var result = userService.register(dtoUser, response);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginUserRequestDto dtoUser,
            HttpServletResponse response) {
        var result = userService.login(dtoUser, response);
        return ResponseEntityHelper.toResponseEntity(result);
    }
}
