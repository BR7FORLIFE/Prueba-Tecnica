package com.todo.ToDo.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.todo.ToDo.enums.Rol;
import com.todo.ToDo.interfaces.UserInterface;
import com.todo.ToDo.models.UserModel;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository implements UserInterface {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<UserModel> userMapper = (resultSet, index) -> {
        UserModel userModel = new UserModel();
        userModel.setId(resultSet.getLong("id"));
        userModel.setUsername(resultSet.getString("username"));
        userModel.setPassword(resultSet.getString("password"));
        userModel.setRol(Rol.valueOf(resultSet.getString("rol")));
        return userModel;
    };

    @Override
    public List<UserModel> listAllUSers(){
        String query = "SELECT username, password, rol FROM Users";
        List<UserModel> listAllusers = jdbcTemplate.query(query, userMapper);
        return listAllusers;
    }

    @Override
    public void saveUser(UserModel userModel) {
        String query = "INSERT INTO Users (username, password, rol) VALUES (?,?,?)";

        jdbcTemplate.update(query, userModel.getUsername(), userModel.getPassword(), userModel.getRol());
    }

    @Override
    public void deleteUser(Long id) {
        String query = "DELETE FROM Users WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public UserModel getUserById(Long id) {
        String query = "SELECT username, password, rol FROM Users WHERE id = ?";
        UserModel user = jdbcTemplate.queryForObject(query, userMapper, id);
        return user;
    }

}
