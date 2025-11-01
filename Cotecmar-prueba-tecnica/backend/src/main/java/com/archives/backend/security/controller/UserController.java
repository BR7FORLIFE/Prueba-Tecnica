package com.archives.backend.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> register(@RequestBody @Valid RegisterUserRequestDto dtoUser) {
        Result<RegisterUserResponseDto, Exception> result = userService.register(dtoUser);
        return ResponseEntityHelper.toResponseEntity(result);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDto> login(@RequestBody @Valid LoginUserRequestDto dtoUser,
            HttpServletResponse response) {
        Result<LoginUserResponseDto, Exception> result = userService.login(dtoUser, response);
        return ResponseEntityHelper.toResponseEntity(result);
    }
}
