package com.archives.backend.security.services;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.archives.backend.security.dtos.request.LoginUserRequestDto;
import com.archives.backend.security.dtos.request.RegisterUserRequestDto;
import com.archives.backend.security.dtos.response.LoginUserResponseDto;
import com.archives.backend.security.dtos.response.RegisterUserResponseDto;
import com.archives.backend.security.model.UserModel;
import com.archives.backend.security.repository.IUserRepository;
import com.archives.backend.shared.utils.result.Result;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Result<RegisterUserResponseDto, Exception> register(RegisterUserRequestDto dtoUser) {
        boolean existsUser = userRepository.existsByName(dtoUser.username());

        if (existsUser) {
            return Result.error(new Exception("The user is already register!"));
        }

        UserModel userModel = new UserModel();
        userModel.setName(dtoUser.username());
        userModel.setPassword(passwordEncoder.encode(dtoUser.password()));
        userModel.setRols(dtoUser.rols());

        userRepository.save(userModel);

        Instant timestamp = Instant.now();

        RegisterUserResponseDto response = new RegisterUserResponseDto("User register succesfull!",
                Date.from(timestamp));

        return Result.ok(response);
    }

    public Result<LoginUserResponseDto, Exception> login(LoginUserRequestDto userDto){
        return null;
    }
}
