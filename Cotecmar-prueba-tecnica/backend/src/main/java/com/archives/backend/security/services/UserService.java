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

import com.archives.backend.security.CustomUserDetails;
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

    public Result<RegisterUserResponseDto, Exception> register(RegisterUserRequestDto dtoUser,
            HttpServletResponse response) {
        boolean existsUser = userRepository.existsByUsername(dtoUser.username());

        if (existsUser) {
            Cookie expiredCookie = new Cookie("AUTH_TOKEN", "");
            expiredCookie.setHttpOnly(true);
            expiredCookie.setMaxAge(0);
            expiredCookie.setPath("/");
            expiredCookie.setSecure(true);

            response.addCookie(expiredCookie);
            return Result.error(new Exception("The user is already register!"));
        }

        UserModel userModel = new UserModel();
        userModel.setUsername(dtoUser.username());
        userModel.setPassword(passwordEncoder.encode(dtoUser.password()));
        userModel.setRols(Set.of(RolUser.USER));

        userRepository.save(userModel);

        Instant timestamp = Instant.now();

        // generamos el token jwt para el nuevo usuario registrado!
        UserDetails userDetailsAuth = new CustomUserDetails(userModel);

        String token = jwtServices.generateJwt(userDetailsAuth);

        // enviamos la cookie
        Cookie cookie = new Cookie("AUTH_TOKEN", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        cookie.setSecure(false);

        // añadimos en la response
        response.addCookie(cookie);

        RegisterUserResponseDto responseDto = new RegisterUserResponseDto("User register succesfull!",
                Date.from(timestamp), token);

        return Result.ok(responseDto);
    }

    public Result<LoginUserResponseDto, Exception> login(LoginUserRequestDto userDto, HttpServletResponse response) {
        try {

            UserModel prove = userRepository.findByUsername(userDto.username()).orElseThrow();
            System.out.println(">>> Raw password: " + userDto.password());
            System.out.println(">>> Encoded in DB: " + prove.getPassword());
            System.out.println(">>> Matches: " + passwordEncoder.matches(userDto.password(), prove.getPassword()));

            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userDto.username(), userDto.password()));

            UserDetails user = (UserDetails) auth.getPrincipal();

            String token = jwtServices.generateJwt(user);

            Cookie cookie = new Cookie("AUTH_TOKEN", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60);

            response.addCookie(cookie);

            LoginUserResponseDto responseUser = new LoginUserResponseDto(token, "Token generated succesfull!");

            return Result.ok(responseUser);

        } catch (AuthenticationException e) {
            Cookie expiredCookie = new Cookie("AUTH_TOKEN", "");
            expiredCookie.setHttpOnly(false);
            expiredCookie.setMaxAge(0);
            expiredCookie.setPath("/");
            expiredCookie.setSecure(true);

            return Result.error(new Exception("Fatal authentication in current user!"));
        }
    }
}
