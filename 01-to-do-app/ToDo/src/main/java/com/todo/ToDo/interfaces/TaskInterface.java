package com.todo.ToDo.interfaces;

import java.util.List;

import com.todo.ToDo.models.Task;
import com.todo.ToDo.models.UserModel;

public interface TaskInterface {
    List<Task> listOfTask(UserModel userModel);
    void createTask(Task task,UserModel userModel);
    Task taskById(Long taskId,UserModel userModel);
    void removeTaskById(Long taskId,UserModel userModel);
}
