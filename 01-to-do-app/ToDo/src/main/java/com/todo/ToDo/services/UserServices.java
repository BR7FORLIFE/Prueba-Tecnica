package com.todo.ToDo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.ToDo.models.UserModel;
import com.todo.ToDo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServices{
    
    private final UserRepository userRepository;

    public List<UserModel> getAllUsers(){
        return userRepository.listAllUSers();
    }
}
