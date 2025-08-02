package com.todo.ToDo.models;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.todo.ToDo.enums.Rol;

import lombok.Data;

@Data
public class UserModel implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Rol rol;
    private List<Rol> rols;

    @Override
    public String getPassword() {
        return username;
    }

    @Override
    public String getUsername() {
        return password;
    }

    public String getRol(){
        return rol.name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rols.stream()
                    .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.name()))
                    .collect(Collectors.toList());
    }
}
