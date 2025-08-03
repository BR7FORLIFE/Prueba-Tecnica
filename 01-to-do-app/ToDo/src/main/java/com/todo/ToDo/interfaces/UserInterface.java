package com.todo.ToDo.interfaces;

import com.todo.ToDo.models.UserModel;

public interface UserInterface {
    void saveUser(UserModel userModel);
    void deleteUser(Long id);
    UserModel getUserById(Long id);
}
