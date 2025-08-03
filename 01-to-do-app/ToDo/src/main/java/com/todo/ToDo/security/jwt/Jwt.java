package com.todo.ToDo.security.jwt;

import org.springframework.stereotype.Service;

import com.todo.ToDo.models.UserModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Jwt {
    
    public String generateToken(UserModel userModel){
        return null;
    }
}
