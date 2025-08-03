package com.todo.ToDo.security.DetailsServices;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todo.ToDo.enums.Rol;
import com.todo.ToDo.models.UserModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServices implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    private RowMapper<UserModel> userMapper = (resultSet, index) -> {
        UserModel userModel = new UserModel();
        userModel.setId(resultSet.getLong("id"));
        userModel.setPassword(resultSet.getString("password"));
        userModel.setRol(Rol.valueOf(resultSet.getString("rol")));
        userModel.setUsername(resultSet.getString("username"));
        return userModel;
    };

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try {
            String query = "SELECT id, username, password, rol WHERE id = ?";
            UserModel userModel = jdbcTemplate.queryForObject(query,userMapper, username);
            return userModel;

        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("username not found in database!");
        }
    }
    
}
