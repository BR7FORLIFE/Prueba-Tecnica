package com.files.__airplane_agendant.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.files.__airplane_agendant.Dtos.users.AuthenticationRequest;
import com.files.__airplane_agendant.models.User;
import com.files.__airplane_agendant.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void saveUser(AuthenticationRequest userRequest){
        User user = User.builder()
                    .id(UUID.randomUUID())
                    .identification(userRequest.getIdentification())
                    .passport(userRequest.getPassport())
                    .password(passwordEncoder.encode(userRequest.getPassword()))
                    .reservations(null)
                    .roles(null)
                    .build();

        userRepository.save(user);
    }

    public void deleteUserById(UUID id){
        userRepository.deleteById(id);
    }
}
