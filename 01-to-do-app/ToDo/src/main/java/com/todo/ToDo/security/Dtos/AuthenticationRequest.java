package com.todo.ToDo.security.Dtos;

import com.todo.ToDo.enums.Rol;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private Long id;
    private String username;
    private String password;
    private Rol rol;
}
