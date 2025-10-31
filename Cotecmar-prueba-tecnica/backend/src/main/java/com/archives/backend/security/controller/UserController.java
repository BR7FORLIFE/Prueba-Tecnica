package com.archives.backend.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archives.backend.security.dtos.request.LoginUserRequestDto;
import com.archives.backend.security.dtos.request.RegisterUserRequestDto;
import com.archives.backend.security.dtos.response.LoginUserResponseDto;
import com.archives.backend.security.dtos.response.RegisterUserResponseDto;
import com.archives.backend.security.services.UserService;
import com.archives.backend.shared.utils.helper.ResponseEntityHelper;
import com.archives.backend.shared.utils.result.Result;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${jwt.expiration-ms}")
    private Long expirationTime;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> register(@RequestBody RegisterUserRequestDto dtoUser) {
        Result<RegisterUserResponseDto, Exception> result = userService.register(dtoUser);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDto> login(@RequestBody @Valid LoginUserRequestDto dtoUser) {

        Result<LoginUserResponseDto, Exception> result = null;

        return null;
    }

}
