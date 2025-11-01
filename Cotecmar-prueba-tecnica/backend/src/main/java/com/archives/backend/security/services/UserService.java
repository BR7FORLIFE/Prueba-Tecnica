package com.archives.backend.security.services;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.archives.backend.security.dtos.request.LoginUserRequestDto;
import com.archives.backend.security.dtos.request.RegisterUserRequestDto;
import com.archives.backend.security.dtos.response.LoginUserResponseDto;
import com.archives.backend.security.dtos.response.RegisterUserResponseDto;
import com.archives.backend.security.enums.RolUser;
import com.archives.backend.security.model.UserModel;
import com.archives.backend.security.repository.IUserRepository;
import com.archives.backend.shared.utils.jwt.JwtServices;
import com.archives.backend.shared.utils.result.Result;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtServices jwtServices;

    public Result<RegisterUserResponseDto, Exception> register(RegisterUserRequestDto dtoUser) {
        boolean existsUser = userRepository.existsByName(dtoUser.username());

        if (existsUser) {
            return Result.error(new Exception("The user is already register!"));
        }

        UserModel userModel = new UserModel();
        userModel.setName(dtoUser.username());
        userModel.setPassword(passwordEncoder.encode(dtoUser.password()));
        userModel.setRols(Set.of(RolUser.USER));

        userRepository.save(userModel);

        Instant timestamp = Instant.now();

        RegisterUserResponseDto response = new RegisterUserResponseDto("User register succesfull!",
                Date.from(timestamp));

        return Result.ok(response);
    }

    public Result<LoginUserResponseDto, Exception> login(LoginUserRequestDto userDto, HttpServletResponse response) {
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userDto.username(), userDto.password()));

            UserDetails user = (UserDetails) auth.getPrincipal();

            String token = jwtServices.generateJwt(user);

            Cookie cookie = new Cookie("AUTH_TOKEN", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24);

            response.addCookie(cookie);

            LoginUserResponseDto responseUser = new LoginUserResponseDto(token, "Token generated succesfull!");

            return Result.ok(responseUser);

        } catch (AuthenticationException e) {
            return Result.error(new Exception("Fatal authentication in current user!"));
        }
    }
}
